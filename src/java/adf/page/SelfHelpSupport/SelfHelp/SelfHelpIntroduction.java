/*
 * Video Integration source taken from org.wicketstuff.html5.media.video.VideoDemo
 *  From webpage: http://grepcode.com/file/repo1.maven.org/maven2/org.wicketstuff/wicket-html5-examples/1.4.9.1/org/wicketstuff/html5/media/video/VideoDemo.java?av=f
 *  Author: Andrew Lombardi
 *
 * Edited by: Miel Zozaya
 */
package adf.page.SelfHelpSupport.SelfHelp;

import org.wicketstuff.html5.media.MediaSource;
import org.wicketstuff.html5.media.video.Html5Video;
import java.util.List;
import java.util.ArrayList;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path ="SelfHelpSupport/SelfHelpStep/Introduction")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SelfHelpIntroduction extends SelfHelpBasePage {

    public SelfHelpIntroduction() {
        super(SelfHelpIntroduction.class);

        /*Code taken from reference avobe*/
        final List<MediaSource> mm = new ArrayList<MediaSource>();
        mm.add(new MediaSource("../assets/sampleVideo.mp4", "video/mp4"));
        mm.add(new MediaSource("../assets/sampleVideo.ogv", "video/ogg"));
        mm.add(new MediaSource("../assets/sampleVideo.webm", "video/webm")); // Video added for IE9 support

        IModel<List<MediaSource>> mediaSourceList = new AbstractReadOnlyModel<List<MediaSource>>() {

            private static final long serialVersionUID = 1L;

            public List<MediaSource> getObject() {
                return mm;
            }
        };

        border.add(new Html5Video("sampleVideo", mediaSourceList) {

            private static final long serialVersionUID = 1L;

            // Edited to accomodate our video
            @Override
            protected int getHeight() {
                return 354;
            }

            @Override
            protected int getWidth() {
                return 624;
            }

            @Override
            protected boolean isAutoBuffer() {
                return true;
            }
            // *******************

            @Override
            protected boolean isControls() {
                return true;
            }

            @Override
            protected boolean isAutoPlay() {
                return true;
            }
        });
    }

    @Override
    protected pageTypes getPageType() {
        return pageTypes.INFORMATION;
    }
}
