package adf.page;

import adf.model.Demographics;
import adf.model.ViewHistory;
import adf.model.RegistrationInformation;
import adf.service.AdfService;
import java.util.Date;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;


public class AdfSession extends AuthenticatedWebSession
{
    final static private Logger LOG = Logger.getLogger(AdfSession.class.getCanonicalName());
    private RegistrationInformation registrationInformation = null;
    private ViewHistory viewHistory = null;

//    public AdfSession(final AuthenticatedWebApplication application, Request request)
//    {
//        super(application, request);
//    }
    public AdfSession(Request request)
    {
        super(request);

    }


    public boolean authenticate(String username, String password)
    {
        return getAdfService().authenticate(username, password);
    }

    /**
     * Returns the current user roles.
     *
     * @return current user roles
     */
    public Roles getRoles()
    {
        if (isSignedIn())
        {
            Roles roles = new Roles();

            GrantedAuthority[] authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities)
            {
                roles.add(authority.getAuthority());
            }
            return roles;
        }
        return null;
    }

    /**
     * @return the currently logged in username, or null when no user is logged in
     */
    public String getUserName()
    {
        String user = null;
        if (isSignedIn())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return user;
    }

    /**
     * Signout, invalidates the session. After a signout, you should redirect the browser to the home adf.page.
     */
    public void signout()
    {
        String user = getUserName();
        if (user != null)
        {
            LOG.info("Logout by user '" + user + "'.");
        }
        setAuthentication(null);
        invalidate();
    }

    private void setAuthentication(Authentication authentication)
    {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public AdfService getAdfService()
    {
        return ((AdfApplication)AdfApplication.get()).getAdfService();
    }

    // Methods for traking user activity. This are called from MyRequestCycle.
    public void startTracking(String pageUrl){
        viewHistory = new ViewHistory(new Date(System.currentTimeMillis()),getUserName());
        viewHistory.setPageUrl(pageUrl);
    }

    public void endTracking(){
        if(viewHistory != null){
            viewHistory.setEndDate(new Date(System.currentTimeMillis()));
            getAdfService().addViewHistory(viewHistory);
            viewHistory = null;
        }
    }

    // Methods for keeping the information during a registration
    // This is done in the session to prevent uncompleate registrations due to
    //  session expirity.
    public void initiateRegistration(String username, String email, String telephone) {
        registrationInformation = new RegistrationInformation();
        registrationInformation.setUsername(username);
        registrationInformation.setEmail(email);
        registrationInformation.setTelephone(telephone);
    }

    public RegistrationInformation getRegistrationInformation(){
        return registrationInformation;
    }

    public void setRegistrationInformation (RegistrationInformation registrationInformation){
        this.registrationInformation = registrationInformation;
    }

    public boolean endRegistration() {
        if(registrationInformation != null){

            // Check if the information needed has been inserterd
            if(registrationInformation.getDemographics() !=null &&
                    registrationInformation.getEmail() != null &&
                    registrationInformation.getUsername() != null){
                // Check if it is a professional registration
                if(registrationInformation.getProfessionalPetitionId() != null){
                    if(!getAdfService().registerProfessional(registrationInformation)){
                        return false;
                    }
                    return true;
                }
                // Check if the user is using an invitation from a professional
                if(registrationInformation.getProfessionalUsername() != null){
                    if(!getAdfService().registerClient(registrationInformation)){
                        return false;
                    }
                    return true;
                }

                // If this two conditions fail, it is a normal registration for a Client permissions
                if(!getAdfService().registerClient(registrationInformation)){
                        return false;
                    }
            }
        }

        return true;
    }


}
