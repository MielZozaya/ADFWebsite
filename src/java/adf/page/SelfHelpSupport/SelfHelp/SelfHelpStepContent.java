/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport.SelfHelp;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/SelfHelpStep/Content")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SelfHelpStepContent extends SelfHelpBasePage {

    public SelfHelpStepContent() {
        super(SelfHelpStepContent.class);
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }
}
