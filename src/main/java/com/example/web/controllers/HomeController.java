package com.example.web.controllers;

import com.example.web.domain.Currency;
import com.example.web.repositorys.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @Autowired
    CurrencyRepository repo;
    @GetMapping("/")
    public String ShowHomePage(@ModelAttribute("currency")Currency currency, Model model)
    {
        model.addAttribute("currency",repo.findAll());
        return "index";
    }

}
