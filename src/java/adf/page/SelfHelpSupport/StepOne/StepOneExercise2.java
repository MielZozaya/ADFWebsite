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
@MountPath(path ="SelfHelpSupport/StepOne/Exercise2")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOneExercise2 extends StepOneBasePage {

    public StepOneExercise2() {
        super(StepOneExercise2.class);
        border.add(new Exercise2Panel("exercisePanel", true));
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.EXERCISE;
    }
}
