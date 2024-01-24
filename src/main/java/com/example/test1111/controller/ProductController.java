package com.example.test1111.controller;

import com.example.test1111.model.Products;
import com.example.test1111.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductRepository iProductRepository;

    @GetMapping
    public ModelAndView showHome(){
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("lists", iProductRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("details", iProductRepository.findById(id));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Products());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(Products products){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductRepository.save(products);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView create(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("product",iProductRepository.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView remove(Products products){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductRepository.delete(products);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("productEdit",iProductRepository.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Products products){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductRepository.save(products);
        return modelAndView;
    }



    @PostMapping("/search")
    public ModelAndView edit(@RequestParam(name = "search") String search){
        ModelAndView modelAndView = new ModelAndView("/search");
        List<Products> listSearch = iProductRepository.findAllByNameContaining(search);
        modelAndView.addObject("lists",listSearch);
        return modelAndView;
    }

}
