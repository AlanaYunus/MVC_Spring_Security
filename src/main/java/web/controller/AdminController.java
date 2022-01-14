package web.controller;

import hiber.service.RoleService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import hiber.model.User;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String allUsers(ModelMap model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/addddd")
    public String newPerson(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles());
        return "add";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "successPage";
    }


    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getRoles());
        return "editUser";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "successPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "successPageDelete";
    }

}