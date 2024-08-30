package com.nagoyameshi.nagoyameshi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.form.CategoryRegisterForm;
import com.nagoyameshi.nagoyameshi.form.CategorySearchForm;
import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequestMapping("category")
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    // 一覧表示
    @GetMapping("list")
    public String list(Model model){
        List<CategoryEntity> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "index";
    }

    // 検索
    @PostMapping("search")
    public String search(Model model, @ModelAttribute @Validated CategorySearchForm categorySearchForm){
        return "index";
    }

    // 登録
    @PostMapping("register")
    public String register(Model model, @ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm){
        return "index";
    }

    // 削除
    @GetMapping("delete")
    public String delete(Model model, @PathVariable(name = "category") Integer categoryId){
        return "index";
    }
}
