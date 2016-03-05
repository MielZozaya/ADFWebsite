/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Common;

import adf.page.MenuItem;
import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 *
 * @author miel
 */
public class Feedback extends AdfWebPage {

    public Feedback() {
        this(-1);
    }


    public Feedback(int currentSidebarItem) {
        List<MenuItem> sidebarLinks = Arrays.asList(
                new MenuItem(new BookmarkablePageLink("tablink",FeedbackFamilyMembers.class),"Family Members",false),
                new MenuItem(new BookmarkablePageLink("tablink",FeedbackProfessionals.class),"Professionals",false));

        AdfBorder border = new AdfBorder("adfBorder",2,sidebarLinks, "Contents",currentSidebarItem);
        add(border);
    }
}
