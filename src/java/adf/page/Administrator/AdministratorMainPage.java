/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Administrator;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Administrator/Main")
@AuthorizeInstantiation({"ROLE_admin"})
public class AdministratorMainPage extends AdministrationBasePage{

    public AdministratorMainPage() {
        super(null);
    }

}
