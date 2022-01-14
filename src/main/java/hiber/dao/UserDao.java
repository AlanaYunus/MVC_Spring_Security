package hiber.dao;

import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserDao extends UserDetailsService {
    List<User> allUsers();

    void add(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long id);

    User findByUsername(String username);

    public UserDetails loadUserByUsername(String username);

}
