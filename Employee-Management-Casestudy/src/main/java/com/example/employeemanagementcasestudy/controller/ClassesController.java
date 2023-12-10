package com.example.employeemanagementcasestudy.controller;

import com.example.employeemanagementcasestudy.dto.ClassDto;
import com.example.employeemanagementcasestudy.model.Classes;
import com.example.employeemanagementcasestudy.service.IClassesService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private IClassesService classesService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping
    public String showList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String searchingName, Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("class_name").ascending());
        Page<Classes> classesList = classesService.findAllPage(searchingName, pageable);
        model.addAttribute("classesList", classesList);
        model.addAttribute("searchingName", searchingName);
        return "/classes/list";
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newClassDto", new Classes());
        return "/classes/create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute ClassDto newClassDto, RedirectAttributes attributes, BindingResult bindingResult, Model model) {
        Classes newClass =  new Classes();
        new ClassDto().validate(newClassDto, bindingResult);
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("newClassDto",newClassDto);
            attributes.addFlashAttribute("validationMessage", "Lớp học cần bắt đầu bằng A,B hoặc C và bao gồm 7 ký tự chữ cái hoặc số");
            return "redirect:/classes/create";
        }
        BeanUtils.copyProperties(newClassDto, newClass);
        if (classesService.findByName(newClass.getClassName()) == null) {
            newClass.setStatus(true);
            classesService.addClass(newClass);
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:/classes";
        } else {
            attributes.addFlashAttribute("validationMessage", "Lớp này đã có rồi, vui lòng kiểm tra lại");
            return "redirect:/classes/create";
        }
    }

    @GetMapping("/edit")
    public String showUpdateForm(@RequestParam int id, Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (!userRoleService.isUserAdmin(principal.getName())) {
//            return "redirect:/403";
//        }
        Classes editingClass = classesService.findById(id);
        ClassDto editingClassDto = new ClassDto();
        BeanUtils.copyProperties(editingClass, editingClassDto);
        model.addAttribute("editingClassDto", editingClassDto);
        return "/classes/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ClassDto editingClassDto, RedirectAttributes attributes, BindingResult bindingResult, Model model) {
        Classes editingClass = new Classes();
        new ClassDto().validate(editingClassDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("editingClassDto", new ClassDto());
            attributes.addFlashAttribute("unsuccess", "Lớp học cần bắt đầu bằng A,B hoặc C và bao gồm 7 ký tự chữ cái hoặc số");
            return "redirect:/classes/edit?id=" + editingClassDto.getClassId();
        }
        BeanUtils.copyProperties(editingClassDto, editingClass);

        if (classesService.findByName(editingClass.getClassName()) == null) {
            editingClass.setStatus(true);
            classesService.addClass(editingClass);
            attributes.addFlashAttribute("edit", "Đã sửa thành công!");
            return "redirect:/classes";
        } else {
            int id = editingClass.getClassId();
            attributes.addFlashAttribute("unsuccess", "Tên lớp này đã tồn tại, vui lòng kiểm tra lại!");
            return "redirect:/classes/edit?id=" + id;
        }
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes attributes,Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (!userRoleService.isUserAdmin(principal.getName())) {
//            return "redirect:/403";
//        }
        classesService.remove(id);
        attributes.addFlashAttribute("delete", "Xoá thành công");
        return "redirect:/classes";
    }

}
