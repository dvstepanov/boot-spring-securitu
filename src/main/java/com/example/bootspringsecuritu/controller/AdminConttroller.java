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

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminConttroller {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @RequestMapping()
    public String listUsers(ModelMap model, Principal principal) {
        model.addAttribute( "user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("listRole", roleService.listRoles());

        return "admin";
    }

    @GetMapping("/add")
    public String getUserForm(Model model, Principal principal) {
        model.addAttribute( "user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("listRole", roleService.listRoles());
        model.addAttribute("users", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam(value = "idRole", required = false) Integer[] idRole) {
        return getStringRole(user, idRole);
    }

    @PostMapping(value = "/deletuser/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edituser/{id}")
    public String edit(@PathVariable long id, ModelMap model) {
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