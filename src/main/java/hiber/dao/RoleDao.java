package hiber.dao;

import hiber.model.Role;

import java.util.List;


public interface RoleDao {

    List<Role> getRoles();

    public void add(Role role);

    Role getById(Long id);

    Role findByRolename (String roleName);

}
