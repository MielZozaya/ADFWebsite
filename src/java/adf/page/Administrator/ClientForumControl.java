/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Administrator;

import adf.page.Client.ClientCategoriesPanel;
import adf.page.Client.ClientForumMessageDisplayPanel;
import adf.page.Client.ClientThreadsPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

/**
 *
 * @author miel
 */
@MountPath (path = "ClientForumControl")
@MountQueryString
@AuthorizeInstantiation({"ROLE_admin"})
public class ClientForumControl extends AdministrationBasePage{

    public ClientForumControl() {
        super(ClientForumControl.class);
        border.add(new ClientCategoriesPanel("contentPanel"));
    }

    public ClientForumControl(PageParameters parameters){
        super(ClientForumControl.class);

        if(parameters.getString("category") != null && !parameters.getString("category").isEmpty()){
            border.add(new ClientThreadsPanel("contentPanel", parameters.getString("category")));
        }
        if(parameters.get("messageId") != null){
            border.add(new ClientForumMessageDisplayPanel("contentPanel", new Long(parameters.getLong("messageId"))));
        }
    }

}
