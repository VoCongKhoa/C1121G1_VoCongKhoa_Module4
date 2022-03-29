package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SimpleCalculatorController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showForm() {
        return "index";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String getResult(@RequestParam(defaultValue = "") String calculate,
                            @RequestParam double number1,
                            @RequestParam double number2,
                            Model model) {
        switch (calculate) {
            case "+":
                model.addAttribute("result", number1 + number2);
                break;
            case "-":
                model.addAttribute("result", number1 - number2);
                break;
            case "x":
                model.addAttribute("result", number1 * number2);
                break;
            case ":":
                model.addAttribute("result", number1 / number2);
                break;
        }
        return "index";
    }
}
