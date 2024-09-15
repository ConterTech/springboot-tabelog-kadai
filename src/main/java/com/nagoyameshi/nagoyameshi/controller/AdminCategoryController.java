package com.nagoyameshi.nagoyameshi.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.form.CategoryRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;
import com.nagoyameshi.nagoyameshi.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/category")
@Controller
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    // 一覧表示
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(page = 0, size = 10, sort = "categoryId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<CategoryEntity> categoryPage;

        if (StringUtils.isEmpty(keyword)) {
            categoryPage = categoryRepository.findAll(pageable);
        } else {
            categoryPage = categoryRepository.findByCategoryLike(keyword + "%", pageable);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryPage", categoryPage);

        return "admin/category/index";
    }

    // 登録
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/index";
        }

        categoryService.addCategory(categoryRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを登録しました。");

        return "index";
    }

    // 削除
    @GetMapping("/delete")
    public String delete(Model model, @PathVariable(name = "category") Integer categoryId) {
        CategoryEntity category = categoryRepository.findByCategoryId(categoryId);
        category.setDeleteFlag(true);
        categoryRepository.save(category);

        return "index";
    }
}