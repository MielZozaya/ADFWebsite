package adf.dao;

import adf.model.Role;

import java.util.List;


public interface RoleDAO
{
    public List<Role> getRoles();

    public Role get(String rolename);

    public void save(Role role);

    public void remove(String rolename);
}
