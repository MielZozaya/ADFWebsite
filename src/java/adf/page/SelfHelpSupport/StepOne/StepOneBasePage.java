/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport.StepOne;

import adf.page.MenuItem;
import adf.page.SelfHelpSupport.SelfHelpProgramBasePage;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/StepOne/")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public abstract class StepOneBasePage extends SelfHelpProgramBasePage{


    private List<? extends Class> pageList = Arrays.asList(StepOne.class,  StepOneIntroductionVideo.class,
            StepOneIntroduction.class, StepOneExamplesOfStress.class, StepOneStrainExperienced.class,
            StepOneExercise2.class, StepOneHealthExperiences.class, StepOneExercise3.class,
            Congratulations.class, StepOneEnd.class);
    private Class currentPage;

    public StepOneBasePage(Class currentPage) {
        super(0,
                ((List<MenuItem>) Arrays.asList(
                new MenuItem(new BookmarkablePageLink("tablink", StepOne.class), "StepOne", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneIntroductionVideo.class), "Introduction Video", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneIntroduction.class), "Introduction", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneExamplesOfStress.class), "Examples Of Stress", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneStrainExperienced.class), "Strain Experienced", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneExercise2.class), "Exercise 2", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneHealthExperiences.class), "Health Experiences", false),
                new MenuItem(new BookmarkablePageLink("tablink", StepOneExercise3.class), "Exercise 3", false))),
                "Self-Help",
                (Arrays.asList(StepOne.class,  StepOneIntroductionVideo.class,
            StepOneIntroduction.class, StepOneExamplesOfStress.class, StepOneStrainExperienced.class,
            StepOneExercise2.class, StepOneHealthExperiences.class, StepOneExercise3.class))
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
}
