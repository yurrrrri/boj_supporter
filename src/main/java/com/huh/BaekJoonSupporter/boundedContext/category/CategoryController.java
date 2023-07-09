package com.huh.BaekJoonSupporter.boundedContext.category;

import com.huh.BaekJoonSupporter.boundedContext.category.form.CategoryForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Category> categories = categoryService.getCategoryAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("create")
    public String create(CategoryForm form) {
        return "category/create";
    }

    @PostMapping("create")
    public String create(@Valid CategoryForm form, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/category/create";
        }

        if (!principal.getName().equals("admin")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }

        categoryService.create(form.getName(), form.getAbout());
        return "redirect:/category/list";
    }
}
