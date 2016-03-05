/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Professional;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Professional/")
@AuthorizeInstantiation({"ROLE_professional"})
public class ProfessionalBasePage extends AdfWebPage{

    AdfBorder border;
    private List<? extends Class> pageList = Arrays.asList(MyClients.class, Tools.class);
    public ProfessionalBasePage(Class currentPage) {
        border = new AdfBorder("adfBorder", 5 + pageList.indexOf(currentPage));
        add(border);
    }

}
