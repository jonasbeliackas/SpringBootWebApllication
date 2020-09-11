package com.example.web.controllers;

import com.example.web.domain.Converter;
import com.example.web.repositorys.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ConverterController {
    @Autowired
    CurrencyRepository repo;

    @RequestMapping(value = "/converter", method = RequestMethod.GET)
    public String convertGet(@ModelAttribute("convert") Converter converter, Model model)
    {
        model.addAttribute("convertedValue",(double)0);
        model.addAttribute("currency",repo.findAll());
        return "converter";
    }
    @RequestMapping(value = "/converter", method = RequestMethod.POST)
    public String convertPost(@ModelAttribute("convert") Converter converter,Model model)
    {
        model.addAttribute("currency",repo.findAll());

        model.addAttribute("convertedValue",converter.convertToEur());
        return "converter";
    }
}
