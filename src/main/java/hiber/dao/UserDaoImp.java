package hiber.dao;

import hiber.model.Role;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select e from User e",
                User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String userName) {

        TypedQuery<User> q = entityManager.createQuery
                ("select u from User u where u.userName = :userName", User.class);
        q.setParameter("userName", userName);
        List<User> list = q.getResultList();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public boolean saveUser(User user) {
        User userFromDB = findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        add(user);
        return true;
    }

    public boolean saveAdmin() {
        User admin = new User();
        Set<Role> set = new HashSet<>();
        set.add(new Role("ROLE_ADMIN"));
        set.add(new Role("ROLE_USER"));
        admin.setRoles(set);
        admin.setUserName("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("1234"));
        add(admin);
        return true;
    }

}
