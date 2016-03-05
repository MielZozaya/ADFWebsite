/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport.SelfHelp;

import adf.page.Common.Home;
import adf.page.SelfHelpSupport.StepOne.StepOne;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/SelfHelpStep/End")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SelfHelpEnd extends SelfHelpBasePage{

    public SelfHelpEnd() {
        super(SelfHelpEnd.class);
        border.add(new BookmarkablePageLink("begin", SelfHelpStep.class));
        border.add(new BookmarkablePageLink("homePage", Home.class));
        border.add(new BookmarkablePageLink("nextStep", StepOne.class));
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }

}
