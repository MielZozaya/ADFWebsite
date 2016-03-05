/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Professional;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path = "Professional/Tools")
@AuthorizeInstantiation({"ROLE_professional"})
public class Tools extends ProfessionalBasePage{

    public Tools() {
        super(Tools.class);

        border.add(new ToolListView("tools", getAdfService().getTools()));
    }

}
