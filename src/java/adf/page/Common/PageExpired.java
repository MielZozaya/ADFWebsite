// ID:105920
package adf.page.Common;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 *
 * @author 
 */
public class PageExpired extends AdfWebPage{

    public PageExpired() {
        AdfBorder border = new AdfBorder("adfBorder",-1);
        add(border);
        Link homeLink = new Link("linkToHome") {

            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        };
        border.add(homeLink);
    }

}
