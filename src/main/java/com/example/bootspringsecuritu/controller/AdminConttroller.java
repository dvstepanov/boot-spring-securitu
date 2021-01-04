package com.example.bootspringsecuritu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.bootspringsecuritu.model.Role;
import com.example.bootspringsecuritu.model.User;
import com.example.bootspringsecuritu.service.UserService;
import com.example.bootspringsecuritu.service.RoleService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminConttroller {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @RequestMapping()
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        System.out.println(roleService.listRoles().toString());
        return "admin";
    }

    @GetMapping("/add")
    public String getUserForm(Model model) {
        model.addAttribute("listRole", roleService.listRoles());
        model.addAttribute("users", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam(value = "idRole", required = false) Integer[] idRole) {
        return getStringRole(user, idRole);
    }

    @RequestMapping(value = "/deletuser/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edituser/{id}")
    public String edit(@PathVariable int id, ModelMap model, ModelMap model1) {
        model.addAttribute("usersedit", userService.getUser(id));
        model1.addAttribute("listRole", roleService.listRoles());
        return "edit";
    }

    @PostMapping(value = "/edituser/{id}")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(value = "idRole", required = false) Integer[] idRole) {
        return getStringRole(user, idRole);
    }

    private String getStringRole(@ModelAttribute User user, @RequestParam(value = "idRole", required = false) Integer[] idRole) {
        if (idRole != null){
            Set<Role> setRole = new HashSet<>();
            for (Integer role : idRole) {
                setRole.add(roleService.findRoleById(role.longValue()));
            }
            user.setRoles(setRole);
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

}