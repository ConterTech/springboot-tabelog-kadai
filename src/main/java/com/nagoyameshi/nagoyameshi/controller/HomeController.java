package com.nagoyameshi.nagoyameshi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    // homeページ
    @GetMapping
    public String index(@RequestParam(name = "store", required = false, defaultValue = "") String store,
            @RequestParam(name = "categoryId", required = false, defaultValue = "") Integer categoryId,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<StoreEntity> storePage;
        List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();
        categoryList = categoryRepository.findAll();

        if (StringUtils.isNotEmpty(store)) {
            storePage = storeRepository.findByStoreNameLike(store + "%", pageable);
        } else if (categoryId != null) {
            CategoryEntity category = categoryRepository.getReferenceById(categoryId);
            storePage = storeRepository.findByCategoryId(category, pageable);
        } else {
            storePage = storeRepository.findAll(pageable);
        }

        model.addAttribute("storePage", storePage);
        model.addAttribute("store", store);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryList", categoryList);

        return "index";
    }
}
