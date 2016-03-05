/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport.StepOne;

import adf.page.SelfHelpSupport.SelfHelpProgramBasePage;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/StepOne/StepOne")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOne  extends StepOneBasePage{

    public StepOne() {
        super(StepOne.class);
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }

    

}
