/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport;

import adf.page.MenuItem;
import adf.page.SelfHelpSupport.FeedbackStep.FeedbackStep;
import adf.page.SelfHelpSupport.SelfHelp.SelfHelpStep;
import adf.page.SelfHelpSupport.SelfHelpProgramBasePage;
import adf.page.SelfHelpSupport.StepFive.StepFive;
import adf.page.SelfHelpSupport.StepFour.StepFour;
import adf.page.SelfHelpSupport.StepOne.StepOne;
import adf.page.SelfHelpSupport.StepThree.StepThree;
import adf.page.SelfHelpSupport.StepTwo.StepTwo;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/Main")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SelfHelpProgramMain extends SelfHelpProgramBasePage {

    public SelfHelpProgramMain() {
        super(-1,
                ((List<MenuItem>) Arrays.asList(new MenuItem(new BookmarkablePageLink("tablink", SelfHelpStep.class), "Self-help", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOne.class), "Step One", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepTwo.class), "Step Two", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepThree.class), "Step Three", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepFour.class), "Step Four", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepFive.class), "Step Five", false),
                new MenuItem(new BookmarkablePageLink("tablink", FeedbackStep.class), "Feedback", false)))
                , "Self-Help Program Steps", -1);
        border.add(new Link("allAnswers") {

            @Override
            public void onClick() {
                setResponsePage(new SelfHelpProgramAllAnswers(getAdfSession().getUserName()));
            }
        });
    }

    @Override
    public int getPageIndex() {
        return 0;
    }

    @Override
    public List<? extends Class> getSequencePageList() {
        return null;
    }

    @Override
    public pageTypes getPageTypes() {
        return pageTypes.INFORMATION;
    }

    @Override
    protected boolean isSequenceNavigable() {
        return false;
    }
}
