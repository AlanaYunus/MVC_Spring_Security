package hiber.service;

import hiber.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getRoles();

    public void add(Role role);

    Role getById(Long id);
}
