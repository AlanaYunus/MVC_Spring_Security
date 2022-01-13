package hiber.service;

import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppService extends UserDetailsService {
    List<User> allUsers();

    void add(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long id);

    public UserDetails loadUserByUsername(String username);

    public boolean saveUser(User user);

    public boolean saveAdmin(List<String> roles);

    public List<Role> getRoles();

}
