// ID:1054920
package adf.page;


import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;


public class ErrorPage extends AdfWebPage
{
    private String errorInfo = "We are sorry but an error occurred!";

    public ErrorPage()
    {
        Border border = new AdfBorder("adfBorder",-1);
        add(border);
        border.add(new Label("info", getErrorInfo()));
    }

    public ErrorPage(String cause, Page page) {
        String pagePath = page != null? page.getPath() : "some page";
        setErrorInfo("We are sorry but an error ocurred in '"+pagePath+ "'. Cause:\n " +
                cause);
        Border border = new AdfBorder("adfBorder",-1);
        add(border);
        border.add(new Label("info", getErrorInfo()));
    }

    public String getErrorInfo()
    {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo)
    {
        this.errorInfo = errorInfo;
    }

}
