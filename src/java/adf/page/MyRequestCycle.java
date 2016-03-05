// ID: 1054920
package adf.page;

import adf.page.Common.PageExpired;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Level;
import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.AcegiSecurityException;
import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.hibernate.HibernateException;

/**
 *
 * @author 
 */
public final class MyRequestCycle extends WebRequestCycle {

    private static Logger logger = Logger.getLogger(MyRequestCycle.class.getCanonicalName());

    /**
     * MyRequestCycle constructor
     *
     * @param application the web application
     * @param request the web request
     * @param response the web response
     */
    public MyRequestCycle(final WebApplication application, final WebRequest request, final Response response) {
        super(application, request, response);
    }

    @Override
    protected void onEndRequest() {
        String path = getWebRequest().getPath();

        // Filter request that are not pages
        if (this.getResponsePageClass() != null) {
            if (urlFor(this.getResponsePageClass(), PageParameters.NULL).toString().contains(path)) {

                AdfSession session = ((AdfSession) getSession());
                // end previous tracking
                session.endTracking();

                // set new traking depending on conditions
                boolean trackablePage = false;

                //Condition: page from path adf.page.SelfHelpSupport
                if (path.contains("SelfHelpSupport")) {
                    trackablePage = true;
                }
                if (trackablePage) {
                    session.startTracking(RequestUtils.toAbsolutePath(urlFor(this.getResponsePageClass(), PageParameters.NULL).toString()));
                }
            }
        }
        super.onEndRequest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Page onRuntimeException(final Page page, final RuntimeException e) {
        // obviously you can check the instanceof the exception and return the appropriate page if desired
        logger.error(e);
        System.err.print(e);
        if (e instanceof PageExpiredException) {
            return new PageExpired();
        }
        if (e instanceof HibernateException) {
            return new ErrorPage("A problem occurred when accessing the database. (" + e.getMessage() + ")", page);
        }

        if (e instanceof NullPointerException) {
            return new ErrorPage("The system had an internal problem while performing the task. (" + e.getMessage() + ")", page);
        }

        if (e instanceof AccessDeniedException) {
            return new ErrorPage("You don't have permissions to acces this page", page);
        }
        if (e instanceof AcegiSecurityException) {
            return new ErrorPage("A security exception arised while performing the task. (" + e.getMessage() + ")", page);
        }

        if (e instanceof Exception) {
            return new ErrorPage(e.getMessage(), page);
        }
        return null;
    }
}
