/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport.SelfHelp;

import adf.page.MenuItem;
import adf.page.SelfHelpSupport.SelfHelpProgramBasePage;
import adf.page.JavaScriptEventConfirmation;
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
@MountPath(path ="SelfHelpSupport/SelfHelpStep/")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public abstract class SelfHelpBasePage extends SelfHelpProgramBasePage {

    
    private List<? extends Class> pageList = Arrays.asList(SelfHelpStep.class, SelfHelpIntroduction.class, SelfHelpStepContent.class, SelfHelpStepExercise1.class, Congratulations.class, SelfHelpEnd.class);
    private Class currentPage;

    public SelfHelpBasePage(Class currentPage) {
        super(0,
                ((List<MenuItem>) Arrays.asList(
                new MenuItem(new BookmarkablePageLink("tablink", SelfHelpStep.class), "Self-Help", false),
                new MenuItem(new BookmarkablePageLink("tablink", SelfHelpIntroduction.class), "Introduction", false),
                new MenuItem(new BookmarkablePageLink("tablink", SelfHelpStepContent.class), "Content of Self-Help", false),
                new MenuItem(new BookmarkablePageLink("tablink", SelfHelpStepExercise1.class), "Exercise 1", false))),
                "Self-Help",
                (Arrays.asList(SelfHelpStep.class, SelfHelpIntroduction.class, SelfHelpStepContent.class, SelfHelpStepExercise1.class))
                    .indexOf(currentPage));
        this.currentPage = currentPage;
    }

    @Override
    protected int getPageIndex() {
        return pageList.indexOf(currentPage);
    }

    @Override
    protected List<? extends Class> getSequencePageList() {
        return pageList;
    }

    @Override
    protected boolean isSequenceNavigable() {
        return true;
    }

    protected pageTypes getPageTypes(){
        return getPageType();
    }

    protected abstract pageTypes getPageType();

    

//   
}
