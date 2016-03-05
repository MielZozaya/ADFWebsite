package adf.dao.hibernate;

import adf.dao.RoleDAO;
import adf.model.Role;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

    public List<Role> getRoles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Role get(String rolename) {
        return (Role) getHibernateTemplate().get(Role.class, rolename);
    }

    public void save(Role role) {
        getHibernateTemplate().save(role);
    }

    public void remove(String rolename) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
