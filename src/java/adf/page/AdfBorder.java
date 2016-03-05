package adf.page;

import adf.page.AdfSession;
import adf.page.Administrator.AdministratorMainPage;
import adf.page.Client.ClientForum;
import adf.page.Common.AboutAdf;
import adf.page.Common.AdfSignIn;
import adf.page.Common.Feedback;
import adf.page.Common.Home;
import adf.page.Common.Home;
import adf.page.Professional.MyClients;
import adf.page.Professional.Tools;
import adf.page.SelfHelpSupport.FeedbackStep.FeedbackStep;
import adf.page.SelfHelpSupport.SelfHelpProgramMain;
import adf.page.SelfHelpSupport.SelfHelp.SelfHelpStep;
import adf.page.SelfHelpSupport.StepFive.StepFive;
import adf.page.SelfHelpSupport.StepFour.StepFour;
import adf.page.SelfHelpSupport.StepOne.StepOne;
import adf.page.SelfHelpSupport.StepThree.StepThree;
import adf.page.SelfHelpSupport.StepTwo.StepTwo;
import adf.page.User.InboxPage;
import adf.page.User.MyAccount;
import adf.page.register.RegistrationMainPage;
import java.awt.Panel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;


public class AdfBorder extends Border
{
    private Link homeLink;
    private Link aboutAdfLink;
    private Link feedbackLink;
    private Link myAccountLink;
    private Link clientForumLink;
    private Link toolsLink;
    private Link myClientsLink;
    private Link inboxLink;
    private Link selfhelpSupport;
    private Link administratorPageLink;
    private Link loginLink;
    private Link registerLink;
    private Link logoutLink;

    private WebMarkupContainer sidebar = new WebMarkupContainer("sidebar");
    private WebMarkupContainer sidebar_content = new WebMarkupContainer("sidebar_content");
    private WebMarkupContainer exerciseMenuContent = new WebMarkupContainer("exerciseMenu");
    private NavigationBar sidebarLinks;
    private Label sidebarTitle;

    private List<MenuItem> indexPageTabItems = Arrays.asList(
                new MenuItem(homeLink = new BookmarkablePageLink("tablink",Home.class), "Home", false),
                new MenuItem(aboutAdfLink = new BookmarkablePageLink("tablink",AboutAdf.class), "About ADF", false),
                new MenuItem(feedbackLink = new BookmarkablePageLink("tablink",Feedback.class), "Feedback", false),
                new MenuItem(myAccountLink = new BookmarkablePageLink("tablink", MyAccount.class),"My Account",false),
                new MenuItem(clientForumLink = new BookmarkablePageLink("tablink", ClientForum.class), "Forum", false),
                new MenuItem(myClientsLink = new BookmarkablePageLink("tablink", MyClients.class), "MyClients", false),
                new MenuItem(toolsLink = new BookmarkablePageLink("tablink", Tools.class), "Tools", false),
                new MenuItem(inboxLink = new BookmarkablePageLink("tablink", InboxPage.class), "Inbox", false),
                new MenuItem(selfhelpSupport = new BookmarkablePageLink("tablink", SelfHelpProgramMain.class), " Self-Help Support ", false),
                new MenuItem(administratorPageLink = new BookmarkablePageLink("tablink", AdministratorMainPage.class), "Administrate", false),
                new MenuItem(loginLink = new BookmarkablePageLink("tablink", AdfSignIn.class), "Log in", false),
                new MenuItem(registerLink = new BookmarkablePageLink("tablink",RegistrationMainPage.class), "Register",false),
                new MenuItem(logoutLink = new Link("tablink") {

        @Override
        public void onClick() {
                ((AdfSession) getSession()).signout();
                setResponsePage(Home.class);
        }
    }, "Logout", false));

    private List<MenuItem> exerciseMenuItems = Arrays.asList(
            new MenuItem(new BookmarkablePageLink("tablink", SelfHelpStep.class), "Self-help", false),
            new MenuItem(new BookmarkablePageLink("tablink", StepOne.class), "Step One", false),
            new MenuItem(new BookmarkablePageLink("tablink", StepTwo.class), "Step Two", false),
            new MenuItem(new BookmarkablePageLink("tablink", StepThree.class), "Step Three", false),
            new MenuItem(new BookmarkablePageLink("tablink", StepFour.class), "Step Four", false),
            new MenuItem(new BookmarkablePageLink("tablink", StepFive.class), "Step Five", false),
            new MenuItem(new BookmarkablePageLink("tablink", FeedbackStep.class), "Feedback", false));

    public AdfBorder(String id, int currentTab)
    {
        this(id,currentTab,null,null,-1,-2);
    }

    public AdfBorder(String id,int currentNavigationBarItem, final List<MenuItem> sidebarLinksList, String sidebar_title, int currentSidebarItem){
        this(id, currentNavigationBarItem, sidebarLinksList, sidebar_title, currentSidebarItem, -2);
    }

    public AdfBorder(String id,int currentNavigationBarItem, final List<MenuItem> sidebarLinksList, String sidebar_title, int currentSidebarItem, int currentExerciseMenuItem)
    {
        super(id);

        /*Main  Menu Bar*/
        add(new NavigationBar("mainMenu",currentNavigationBarItem,"menuitemInactive","menuitemActive"){

            @Override
            public List<MenuItem> getMenuItems(){
                return getIndexPageTabItems();
            }
        });

        if(((AdfSession)getSession()).isSignedIn()){
            loginLink.setVisible(false);
            registerLink.setVisible(false);
        } else {
            logoutLink.setVisible(false);
        }
        
        /*menu items depending on permisions*/
        MetaDataRoleAuthorizationStrategy.authorize(myAccountLink, RENDER, "ROLE_client, ROLE_professional, ROLE_admin");
        MetaDataRoleAuthorizationStrategy.authorize(clientForumLink, RENDER, "ROLE_client");
        MetaDataRoleAuthorizationStrategy.authorize(inboxLink, RENDER, "ROLE_client, ROLE_professional");
        MetaDataRoleAuthorizationStrategy.authorize(toolsLink, RENDER, "ROLE_professional");
        MetaDataRoleAuthorizationStrategy.authorize(myClientsLink, RENDER, "ROLE_professional");

        MetaDataRoleAuthorizationStrategy.authorize(selfhelpSupport, RENDER, "ROLE_client, ROLE_professional");
        MetaDataRoleAuthorizationStrategy.authorize(administratorPageLink, RENDER, "ROLE_admin");

        /* Sidebar Links. This wil not display if sidebar list is null*/
        sidebarLinks = new NavigationBar("sidebarLinks",currentSidebarItem,"sidebarItemInactive","sidebarItemActive") {

            @Override
            public List<MenuItem> getMenuItems() {
                return sidebarLinksList;
            }
        };

        sidebarTitle = new Label("sidebarTitle", sidebar_title);

        sidebar.add(sidebarTitle);
        sidebar.add(sidebarLinks);
        add(sidebar);
        add(sidebar_content);
        
        if(sidebarLinksList == null || sidebar_title == null){
            sidebarLinks.setVisible(false);
            sidebarTitle.setVisible(false);
            sidebar_content.add(new SimpleAttributeModifier("class","nosidebar_content"));
        } else {
            sidebar.add(new SimpleAttributeModifier("id", "sidebar"));
            sidebar_content.add(new SimpleAttributeModifier("class", "sidebar_content"));
        }

        /*Exercise menu bar*/
        exerciseMenuContent.add(new NavigationBar("exerciseMenuLinks",currentExerciseMenuItem,"exerciseMenuItemInactive","exerciseMenuItemActive") {

            @Override
            public List<MenuItem> getMenuItems() {
                return exerciseMenuItems;
            }
        });

        add(exerciseMenuContent);

        if(currentExerciseMenuItem == -2 ){
            exerciseMenuContent.setVisible(false);
        } else {
            exerciseMenuContent.add(new SimpleAttributeModifier("id", "exerciseMenu"));
        }
    }

    public List<MenuItem> getIndexPageTabItems(){
            return Collections.unmodifiableList(indexPageTabItems);
    }


}