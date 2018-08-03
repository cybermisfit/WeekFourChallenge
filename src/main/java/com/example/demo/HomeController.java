package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/")
    public String initialForm(Model model){
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transactionhistory";
    }
    @GetMapping("/add")
    public String transactionForm(Model model){
        model.addAttribute("transaction", new Transaction());
        return "form";
    }
    @PostMapping("/process")
    public String processForm(@Valid Transaction transaction, BindingResult result){
        if (result.hasErrors()){
            return "form";
        }
        transactionRepository.save(transaction);
        return "redirect:/";
    }
    @RequestMapping("detail/{id}")
    public String showTransaction(@PathVariable("id") long id, Model model){
        model.addAttribute("transaction", transactionRepository.findById(id).get());
        return "show";
    }

}
