package adf.page;

import org.apache.wicket.markup.html.WebPage;
import adf.service.AdfService;

/**
 * User: Alan P. Sexton
 * Date: 17-Mar-2008
 * Time: 09:55:43
 */
public class AdfWebPage extends WebPage
{
    public AdfSession getAdfSession()
    {
        return (AdfSession)getSession();
    }

    public AdfService getAdfService()
    {
        return getAdfSession().getAdfService();
    }

}
