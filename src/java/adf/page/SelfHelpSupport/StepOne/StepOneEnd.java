/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport.StepOne;

import adf.page.Common.Home;
import adf.page.SelfHelpSupport.StepTwo.StepTwo;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/StepOne/End")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class StepOneEnd extends StepOneBasePage{

    public StepOneEnd() {
        super(StepOneEnd.class);
        border.add(new BookmarkablePageLink("begin", StepOne.class));
        border.add(new BookmarkablePageLink("homePage", Home.class));
        border.add(new BookmarkablePageLink("nextStep", StepTwo.class));
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }

}
