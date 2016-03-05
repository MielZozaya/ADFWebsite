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
@MountPath(path ="SelfHelpSupport/StepOne/Exercise3")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOneExercise3 extends StepOneBasePage {

    public StepOneExercise3() {
        super(StepOneExercise3.class);
        border.add(new Exercise3Panel("exercisePanel", true));
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.EXERCISE;
    }
}