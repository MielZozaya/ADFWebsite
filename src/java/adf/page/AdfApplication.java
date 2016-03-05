package adf.page;

import adf.page.Common.PageExpired;
import adf.page.Common.AdfSignIn;
import adf.page.Common.Home;
import adf.page.SelfHelpSupport.SelfHelp.SelfHelpIntroduction;
import adf.page.SelfHelpSupport.assets.VideoScope;
import org.acegisecurity.AuthenticationManager;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.lang.PackageName;
import adf.service.AdfService;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Response;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadWebRequest;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.util.file.Folder;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

public class AdfApplication extends AuthenticatedWebApplication {

    private AuthenticationManager authenticationManager;
    private AdfService adfService;
    private Folder uploadFolder = null;

    public AdfApplication() {
    }

    @Override
    public void init() {
        super.init();
        mount("/pages", PackageName.forPackage(AdfApplication.class.getPackage()));
        mount("/Common", PackageName.forPackage(adf.page.Common.Home.class.getPackage()));
        mount("/Client/", PackageName.forClass(adf.page.Client.ClientBasePage.class));

        new AnnotatedMountScanner().scanPackage("adf.page").mount(this);

        mountSharedResource("/SelfHelpSupport/assets/sampleVideo.mp4", new ResourceReference(VideoScope.class, "sampleVideo.mp4").getSharedResourceKey());
        mountSharedResource("/SelfHelpSupport/assets/sampleVideo.ogv", new ResourceReference(VideoScope.class, "sampleVideo.ogv").getSharedResourceKey());
        mountSharedResource("/SelfHelpSupport/assets/sampleVideo.webm", new ResourceReference(VideoScope.class, "sampleVideo.webm").getSharedResourceKey());

        addComponentInstantiationListener((IComponentInstantiationListener) new SpringComponentInjector(this));

        // Source From: http://www.wicket-library.com/wicket-examples/upload/?wicket:bookmarkablePage=sources:org.apache.wicket.examples.source.SourcesPage&SourcesPage_class=org.apache.wicket.examples.upload.UploadPage&source=UploadApplication.java
        uploadFolder = new Folder(System.getProperty("java.io.tmpdir"), "wicket-uploads");
        // Ensure folder exists
        uploadFolder.mkdirs();

        // Set a customized (friendly) error adf.page rather than default developer adf.page
        //getApplicationSettings().setInternalErrorPage(ErrorPage.class);


        // Customize your own PageExpiredErrorPage
        getApplicationSettings().setPageExpiredErrorPage(PageExpired.class);


        // Customize your own AccessDeniedPage
        getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);

        // show internal error adf.page rather than default wicket's developer adf.page
        //getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RequestCycle newRequestCycle(final Request request, final Response response) {
        return new MyRequestCycle(this, (WebRequest) request, (WebResponse) response);
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return AdfSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return AdfSignIn.class;
    }

    @Override
    public Class getHomePage() {
        return Home.class;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AdfService getAdfService() {
        return adfService;
    }

    public void setAdfService(AdfService adfService) {
        this.adfService = adfService;
    }

    /**
     * Source from: http://www.wicket-library.com/wicket-examples/upload/?wicket:bookmarkablePage=sources:org.apache.wicket.examples.source.SourcesPage&SourcesPage_class=org.apache.wicket.examples.upload.UploadPage&source=UploadApplication.java
     * @return
     */
    public Folder getUploadFolder() {
        return uploadFolder;
    }

    // source from: http://www.mkyong.com/wicket/how-do-encrypt-encode-url-in-wicket/
//    @Override
//    protected IRequestCycleProcessor newRequestCycleProcessor() {
//
//        return new WebRequestCycleProcessor() {
//
//            @Override
//            protected IRequestCodingStrategy newRequestCodingStrategy() {
//                return new CryptedUrlWebRequestCodingStrategy(new WebRequestCodingStrategy()){
//
//                };
//            }
//        };
//    }

    // source from: http://www.wicket-library.com/wicket-examples/upload/?wicket:bookmarkablePage=sources:org.apache.wicket.examples.source.SourcesPage&SourcesPage_class=org.apache.wicket.examples.upload.UploadPage&source=UploadApplication.java
     @Override
    protected WebRequest newWebRequest(HttpServletRequest servletRequest)
    {
        return new UploadWebRequest(servletRequest);
    }
}
