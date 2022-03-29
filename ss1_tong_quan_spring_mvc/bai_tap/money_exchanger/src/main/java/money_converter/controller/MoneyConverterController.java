package money_converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoneyConverterController {

    @GetMapping(value = {"", "/converter"})
    public String showForm() {
        return "converter";
    }

    @PostMapping(value = {"/convert"})
    public String sum2Num(@RequestParam double money,
                          @RequestParam double rate1,
                          @RequestParam double rate2,
                          Model model) {

        double result = money*rate1/rate2;
        model.addAttribute("result", result);
        return "converter";
    }
}
