package web.config;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.RoleService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class StartData {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void postConstruct() {
        roleService.add(new Role("ROLE_ADMIN"));
        roleService.add(new Role("ROLE_USER"));

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword("1234");
        Set<Role> roleSetAdmin = new HashSet<>();
        roleSetAdmin.add(roleService.getById(1L));
        roleSetAdmin.add(roleService.getById(2L));
        admin.setRoles(roleSetAdmin);

        userService.saveUser(admin);

        User normalUser = new User();
        normalUser.setUserName("bob");
        normalUser.setPassword("1234");
        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.getById(2L));
        normalUser.setRoles(roleSetUser);

        userService.saveUser(normalUser);
    }

}