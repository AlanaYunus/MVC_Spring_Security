package hiber.service;

import hiber.dao.RoleDao;
import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        refresh(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.edit(user);
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    @Transactional
    @Override
    public boolean saveUser(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        refresh(user);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        add(user);
        return true;
    }

    private void refresh(User user) {
        user.setRoles(user.getRoles().stream().map(role -> roleDao.findByRolename(role.getRoleName())).collect(Collectors.toSet()));
    }


//    @Transactional
//    @Override
//    public boolean saveAdmin(List<String> roles) {
//        User admin = new User();
//        Set<Role> roleSet = new HashSet<Role>(roleDao.findByRolename(roles));
//        admin.setRoles(roleSet);
//        admin.setUserName("admin");
//        admin.setPassword(bCryptPasswordEncoder.encode("1234"));
//        add(admin);
//        return true;
//    }
}

