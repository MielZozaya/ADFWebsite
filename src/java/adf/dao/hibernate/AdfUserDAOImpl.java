// ID: 1054920
package adf.dao.hibernate;

import adf.model.InboxMessage;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.dao.DataAccessException;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import adf.dao.AdfUserDAO;
import adf.model.AdfUser;

import java.util.List;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 *
 * @author miel
 */
public class AdfUserDAOImpl extends HibernateDaoSupport implements AdfUserDAO
{

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        try
        {
            AdfUser user = getAdfUser(username);
            return user;
        }
        catch (ObjectRetrievalFailureException e)
        {
            throw new UsernameNotFoundException("user '" + username + "' not found");
        }
    }

    public List<AdfUser> getUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AdfUser getAdfUser(String username) {
        return (AdfUser) getHibernateTemplate().load(AdfUser.class, username);
    }

    public void save(AdfUser user) {
        getHibernateTemplate().save(user);
    }

    public void update(AdfUser user) {
        getHibernateTemplate().update(user);
    }

    public List<InboxMessage> getInboxMessages(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAdfUserRegistered(String username) {
        try
        {
            AdfUser user = (AdfUser) getHibernateTemplate().get(AdfUser.class,username);
            return user != null;
            
        }
        catch (DataAccessException e ){
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
    
}
