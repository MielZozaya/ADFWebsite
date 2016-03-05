/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Client;

import adf.model.ClientCategory;
import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;




/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_admin","ROLE_client"})
public class ClientForumMain extends AdfWebPage{

    private static final Logger logger = Logger.getLogger(ClientForumMain.class.getCanonicalName());

    public ClientForumMain(){

        AdfBorder border = new AdfBorder("adfBorder",3);
        add(border);

        logger.debug("Getting Client Categories.");
        List<ClientCategory> clientCategory = getAdfService().getClientCategories();
        logger.debug("Client Categories getted.");

        ListView categories = new ListView("categories", clientCategory) {

            @Override
            protected void populateItem(ListItem li) {
                final ClientCategory cat = (ClientCategory) li.getModelObject();
                li.add(new Link("categoryLink") {

                    @Override
                    public void onClick() {
                        setResponsePage(new ClientForumMessageBoard(cat.getName()));
                    }
                }.add(new Label("categoryName", cat.getName())));
            }
        };

        border.add(categories);
    }
}
