package com.nagoyameshi.nagoyameshi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.form.CategoryEditForm;
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

    // カテゴリ登録画面表示
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());
        return "admin/category/register";
    }

    // カテゴリ登録
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 登録済みのカテゴリを追加しようとしたときにエラー
        if (categoryService.isCategoryRegistered(categoryRegisterForm.getCategory())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "category", "すでに登録済みのカテゴリです。");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "admin/category/register";
        }

        categoryService.addCategory(categoryRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを登録しました。");

        return "redirect:/admin/category";
    }

    // カテゴリ編集画面表示
    @GetMapping("/{categoryId}/edit")
    public String edit(@PathVariable(name = "categoryId") Integer categoryId, Model model) {
        CategoryEntity category = categoryRepository.getReferenceById(categoryId);
        CategoryEditForm categoryEditForm = new CategoryEditForm(category.getCategoryId(), category.getCategory());
        model.addAttribute("categoryEditForm", categoryEditForm);
        return "admin/category/edit";
    }

    // カテゴリ編集
    @PostMapping("/{categoryId}/update")
    public String update(Model model,
            @ModelAttribute @Validated CategoryEditForm categoryEditForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 登録済みのカテゴリを追加しようとしたときにエラー
        if (categoryService.isCategoryRegistered(categoryEditForm.getCategory())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "category", "すでに登録済みのカテゴリです。");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        }

        categoryService.updateCategory(categoryEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを編集しました。");

        return "redirect:/admin/category";
    }

    // 削除
    @PostMapping("/{categoryId}/delete")
    public String delete(@PathVariable(name = "categoryId") Integer categoryId, RedirectAttributes redirectAttributes,
            Model model) {
        categoryRepository.deleteById(categoryId);

        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");

        return "redirect:/admin/category";
    }
}
