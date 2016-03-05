/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.Professional;

import adf.model.Tool;
import adf.page.AdfApplication;
import adf.page.AdfSession;
import adf.page.JavaScriptEventConfirmation;
import java.io.File;
import java.util.List;
import org.apache.wicket.Application;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;

/**
 *
 * @author miel
 */
public class ToolListView extends ListView {

    public ToolListView(String string, List list) {
        super(string, list);
    }

    @Override
    protected void populateItem(ListItem li) {
        final Tool tool = (Tool) li.getModelObject();
        li.add(new Label("name", tool.getName()));
        li.add(new MultiLineLabel("description", tool.getDescription()));

        final File file = new File(getUploadFolder(), tool.getSrc());
        li.add(new DownloadLink("download", file));

        Link removeLink = new Link("remove") {

            @Override
            public void onClick() {
                ((AdfSession) getSession()).getAdfService().deleteTool(tool.getId());
                Files.remove(file);
                setResponsePage(getPage().getPageClass());
            }
        };
        removeLink.add(new JavaScriptEventConfirmation("onclick", "Are you sure that you want to delete the file?"));
        li.add(removeLink);

        MetaDataRoleAuthorizationStrategy.authorize(removeLink, RENDER, "ROLE_admin");


    }

    private Folder getUploadFolder() {
        return ((AdfApplication) Application.get()).getUploadFolder();
    }
}
