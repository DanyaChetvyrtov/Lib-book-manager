package ru.danil.mvcDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.danil.mvcDemo.service.QuoteService;


@Controller
@RequestMapping("/home")
public class HomeController {
    private final QuoteService quoteService;

    public HomeController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("quote", quoteService.getRandomQuote());
        return "home_page";
    }
}
