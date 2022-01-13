package hiber.dao;

import hiber.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    List<Role> getRoles();

    List<Role> findByRolename (List <String> roleName);

}
