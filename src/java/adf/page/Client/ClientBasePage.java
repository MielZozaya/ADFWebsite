/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Client;

import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.protocol.http.WebResponse;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_client"})
@MountPath(path ="/Client")
public class ClientBasePage extends AdfWebPage{

    AdfBorder border;


    public ClientBasePage(int currentPage) {
        border = new AdfBorder("adfBorder",currentPage);
        add(border);
    }

    
}
