package adf.page.Common;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.border.Border;


public class AdfSignIn extends AdfWebPage
{

    public AdfSignIn()
    {
        this(null);
    }

    public AdfSignIn(final PageParameters parameters)
    {
        Border border = new AdfBorder("adfBorder",10);
        add(border);
        border.add(new SignInPanel("signInPanel", false));
    }

}
