package adf.dao;

import adf.model.InboxMessage;
import adf.model.AdfUser;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface AdfUserDAO extends UserDetailsService
{
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException;

    public List<AdfUser> getUsers();

    public AdfUser getAdfUser(String username);

    public void save(AdfUser user);

    public void update(AdfUser user);

    public List<InboxMessage> getInboxMessages(String username);

    public boolean isAdfUserRegistered(String username);

}
