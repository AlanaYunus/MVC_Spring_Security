package hiber.dao;

import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    @Override
    public User findByUsername(String userName) {

        TypedQuery<User> q = entityManager.createQuery
                ("select u from User u where u.userName = :userName", User.class);
        q.setParameter("userName", userName);
        User result = null;
        try {
            result = q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

}
