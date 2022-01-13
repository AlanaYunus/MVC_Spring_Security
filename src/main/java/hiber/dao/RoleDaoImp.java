package hiber.dao;

import hiber.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("select e from Role e",
                Role.class).getResultList();
    }

    @Override
    public List<Role> findByRolename(List <String> roleNames) {

        List<Role> roles = new ArrayList<>();

        for (String roleName : roleNames) {
            List<Role> found = entityManager.createQuery("select r from Role r where r.roleName = :roleName", Role.class)
                        .setParameter("roleName", roleName).getResultList();
                if (found.isEmpty()) {
                    Role role = new Role(roleName);
                    entityManager.persist(role);
                    roles.add(role);
                } else {
                    roles.addAll(found);
                }
            }
           return roles;
        }
    }


