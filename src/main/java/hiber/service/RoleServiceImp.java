package hiber.service;

import hiber.dao.RoleDao;
import hiber.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Transactional
    @Override
    public void add(Role role) { roleDao.add(role);}

    @Transactional
    @Override
    public Role getById(Long id) {return roleDao.getById(id);};

}
