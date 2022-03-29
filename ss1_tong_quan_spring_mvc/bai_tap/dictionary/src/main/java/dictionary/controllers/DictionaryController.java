package dictionary.controllers;

import dictionary.services.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DictionaryController {

    @Autowired
    private IDictionaryService iDictionaryService;

    @GetMapping(value = "")
    public String showForm(){
        return "dictionary";
    }

    @GetMapping(value = "/meaning")
    public String getMeaning(@RequestParam String word,
                             Model model){
        Map<String,String> dictionaryMap = iDictionaryService.findAll();
        String meaning = dictionaryMap.get(word);
        model.addAttribute("meaning",meaning);
        return "dictionary";
    }
}
