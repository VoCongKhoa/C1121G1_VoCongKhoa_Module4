package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.dto.SmartphoneDto;
import project.models.ResponseObject;
import project.models.Smartphone;
import project.services.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/smartphones")
@CrossOrigin
public class SmartphoneController {
    @Autowired
    private ISmartphoneService smartphoneService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createSmartphone(@Valid @RequestBody SmartphoneDto smartphoneDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
//            for (Map.Entry<String, String> error: errorMap.entrySet()) {
//                System.out.println(error.getKey() + ":" + error.getValue());
//            }
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Smartphone smartphone = new Smartphone();
            BeanUtils.copyProperties(smartphoneDto,smartphone);
            smartphoneService.save(smartphone);
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(),""), HttpStatus.CREATED);
        }
    }
//    @GetMapping("/list")
//    public ModelAndView getAllSmartphonePage() {
//        ModelAndView modelAndView = new ModelAndView("/list");
//        modelAndView.addObject("smartphones", smartphoneService.findAll());
//        return modelAndView;
//    }

    @GetMapping("/view")
    public ResponseEntity<Smartphone> view(@RequestParam Long id){
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Smartphone>> getAllSmartphonePage() {
        return new ResponseEntity<>(smartphoneService.findAll(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Smartphone>> allPhones() {
        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> deleteSmartphone(@PathVariable Long id) {
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/update")
//    public ModelAndView goUpdate(@RequestParam Long id, Model model) {
//        ModelAndView modelAndView = new ModelAndView("update.html");
//        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
//        modelAndView.addObject("smartphone", smartphoneOptional.get());
//        return modelAndView;
//    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseObject> updateSmartphone(@RequestParam Long id, @Valid @RequestBody SmartphoneDto smartphoneDto,
                                                           BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
//            for (Map.Entry<String, String> error: errorMap.entrySet()) {
//                System.out.println(error.getKey() + ":" + error.getValue());
//            }
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",errorMap,""), HttpStatus.BAD_REQUEST);
        }
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneDto.setId(smartphoneOptional.get().getId());
        Smartphone smartphone = new Smartphone();
        BeanUtils.copyProperties(smartphoneDto, smartphone);
        smartphoneService.save(smartphone);
        return new ResponseEntity<>(new ResponseObject("ok","Success!",""), HttpStatus.OK);
//        return new ResponseEntity<>(smartphoneService.save(smartphoneDto), HttpStatus.OK);
    }
}
