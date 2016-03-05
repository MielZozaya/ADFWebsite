/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Administrator;

import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import adf.page.Common.Home;
import adf.page.MenuItem;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_admin"})
public class AdministrationBasePage extends AdfWebPage{

    protected AdfBorder border;

    List<MenuItem> sidebarLinks = Arrays.asList(
                new MenuItem(new BookmarkablePageLink("tablink",ClientForumControl.class),"Client Forum Control",false),
                new MenuItem(new BookmarkablePageLink("tablink",ProfessionalPetitions.class),"Professional Petitions",false),
                new MenuItem(new BookmarkablePageLink("tablink",AdministrateTools.class),"Administrate Tools",false),
                new MenuItem(new BookmarkablePageLink("tablink",DatabaseInformation.class),"Database Information",false));
    List<? extends Class> pageList = Arrays.asList(ClientForumControl.class, ProfessionalPetitions.class, AdministrateTools.class, DatabaseInformation.class);

    public AdministrationBasePage(Class currentPage) {
        border = new AdfBorder("adfBorder",9,sidebarLinks, "Tools",currentPage == null? -1:pageList.indexOf(currentPage));
        add(border);
    }

}
