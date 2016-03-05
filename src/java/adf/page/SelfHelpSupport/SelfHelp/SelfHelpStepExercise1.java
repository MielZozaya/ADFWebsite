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
@MountPath(path ="SelfHelpSupport/SelfHelpStep/Exercise1")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SelfHelpStepExercise1 extends SelfHelpBasePage {

    public SelfHelpStepExercise1() {
        super(SelfHelpStepExercise1.class);
        border.add(new Exercise1Panel("exercisePanel",true));

    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.EXERCISE;
    }
}
