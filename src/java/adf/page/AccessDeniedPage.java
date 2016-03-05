package adf.page;


public class AccessDeniedPage extends ErrorPage
{
    public AccessDeniedPage()
    {
    }

    @Override
    public String getErrorInfo()
    {
        return "Access Denied!";
    }
}
