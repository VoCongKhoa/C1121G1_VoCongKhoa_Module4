package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SandwichController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showForm(){
        return "index";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String getResult(@RequestParam(defaultValue = "") String lettuce,
                            @RequestParam(defaultValue = "") String tomato,
                            @RequestParam(defaultValue = "") String mustard,
                            @RequestParam(defaultValue = "") String sprouts,
                            Model model){
        List<String> tempList = Arrays.asList(lettuce,tomato,mustard,sprouts);
        List<String> condiments = tempList.stream()
                .filter(condiment -> !"".equals(condiment))
                .collect(Collectors.toList());
        String result = condiments.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        model.addAttribute("result",result);
        return "index";
    }
}
