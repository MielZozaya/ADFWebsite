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
@MountPath(path ="SelfHelpSupport/StepOne/HealthExperiences")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOneHealthExperiences extends StepOneBasePage{

    public StepOneHealthExperiences() {
        super(StepOneHealthExperiences.class);
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }

}
