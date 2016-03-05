/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport.StepOne;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/StepOne/ExamplesOfStress")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOneExamplesOfStress extends StepOneBasePage{

    public StepOneExamplesOfStress() {
        super(StepOneExamplesOfStress.class);
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }

}
