package adf.service;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;


public class SecurityAspect
{
    //Users can and only can access their own profile or adfs
    public void doAccessCheck(String lecturer)
            throws Throwable
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = ((UserDetails) authentication.getPrincipal()).getUsername();
        //Uncomment this line for testing
//        currentUser = "hacker";
        if (!currentUser.equals(lecturer))
        {
            throw new AccessDeniedException("Access Denied!");
        }
    }

}
