/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport;

import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import adf.page.JavaScriptEventConfirmation;
import adf.page.MenuItem;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public abstract class SelfHelpProgramBasePage extends AdfWebPage {

    protected AdfBorder border;
    protected Link nextButton;
    protected Link previousButton;

    protected enum pageTypes {

        INFORMATION, EXERCISE
    };

    public SelfHelpProgramBasePage() {
    }

    public SelfHelpProgramBasePage(int currentExerciseMenuItem, List<MenuItem> sidebarLinks, String sidebarTitle, int currentSidebarItem) {
        border = new AdfBorder("adfBorder", 8, sidebarLinks, sidebarTitle, currentSidebarItem, currentExerciseMenuItem);
        add(border);
    }


    @Override
    public void onBeforeRender() {
        if (!isSequenceNavigable()) {
            nextButton = new Link("nextButton") {

                @Override
                public void onClick() {
                }
            };

            border.add(nextButton);


            previousButton = new Link("previousButton") {

                @Override
                public void onClick() {
                }
            };

            border.add(previousButton);
            nextButton.setVisible(false);
            previousButton.setVisible(false);

        } else {
            nextButton = new Link("nextButton") {

                @Override
                public void onClick() {
                    setResponsePage(getPageIndex() == getSequencePageList().size() - 1 ? getSequencePageList().get(getPageIndex()) : getSequencePageList().get(getPageIndex() + 1));
                }
            };

            border.add(nextButton);


            previousButton = new Link("previousButton") {

                @Override
                public void onClick() {
                    setResponsePage(getPageIndex() == 0 ? getSequencePageList().get(getPageIndex()) : getSequencePageList().get(getPageIndex() - 1));
                }
            };

            border.add(previousButton);

            /*Add next button event confirmation if the page type is exercise*/
            switch (getPageTypes()) {
                case EXERCISE: {
                    nextButton.add(new JavaScriptEventConfirmation("onclick", "Wait! Take few minutes and try to think a bit further. If you have discovered further answers click \"cancel\" for contining with the steps press \"confirm\""));
                    break;
                }
                case INFORMATION: {
                }
            }

            /*Set visibilities of next and previous buttons*/
            if (getPageIndex() == 0) {
                previousButton.setVisible(false);

            }
            if (getPageIndex() == getSequencePageList().size() - 1) {
                nextButton.setVisible(false);

            }
        }
        super.onBeforeRender();
    }

   
    protected abstract boolean isSequenceNavigable();

    protected abstract int getPageIndex();

    protected abstract List<? extends Class> getSequencePageList();

    protected abstract pageTypes getPageTypes();
}
