/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.Client;

import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.protocol.http.WebResponse;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountBookmarkablePageRequestTarget;
import org.wicketstuff.annotation.strategy.MountHybrid;
import org.wicketstuff.annotation.strategy.MountMixedParam;
import org.wicketstuff.annotation.strategy.MountQueryString;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_client"})
@MountPath(path= "ClientForum")
//@MountBookmarkablePageRequestTarget(pageMapName= "Forum")
@MountQueryString
public class ClientForum extends ClientBasePage {

    public ClientForum() {
        super(4);
        border.add(new ClientCategoriesPanel("contentPanel"));
    }

    public ClientForum(PageParameters parameters) {
        super(3);

        if (parameters.getString("category") != null && !parameters.getString("category").isEmpty()) {
            border.add(new ClientThreadsPanel("contentPanel", parameters.getString("category")));
        }
        if (parameters.get("messageId") != null) {
            border.add(new ClientForumMessageDisplayPanel("contentPanel", new Long(parameters.getLong("messageId"))));
        }
    }

    
    @Override
    protected void configureResponse() {
        super.configureResponse();

        final WebResponse response = getWebRequestCycle().getWebResponse();
        response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate, no-store");
        response.setHeader("Pragma", "no-cache");
    }

    protected void setHeaders(WebResponse response) {

        response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate, no-store");
    }
}
