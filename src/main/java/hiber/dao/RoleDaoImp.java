package hiber.dao;

import hiber.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public void add(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Transactional
    @Override
    public Role findByRolename(String roleName) {
        TypedQuery<Role> q = entityManager.createQuery
                ("select r from Role r where r.roleName = :roleName", Role.class);
        q.setParameter("roleName", roleName);
        return q.getSingleResult();
        }


    }


