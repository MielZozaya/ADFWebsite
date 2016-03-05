/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Client;

import adf.model.ForumMessage;
import adf.page.AdfSession;
import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_admin","ROLE_client"})
public class ClientThreadsPanel extends Panel{

    Model newMessageBody;
    Model newMessageTitle;

    public ClientThreadsPanel(String id, final String catname) {
        super(id);

        add(new Label("threadsHeader", "Threads:"));
        add(new TreeTablePanel("loadMessages", (List<ForumMessage>) ((AdfSession)getSession()).getAdfService().getClientForumMessagesByCategory(catname)));

        add(new Label("newMessage", "New Thread:"));
        Form form = new Form("Message"){

            @Override
            protected void onSubmit() {
                String body = (String) newMessageBody.getObject();
                String title = (String) newMessageTitle.getObject();

                if(body.isEmpty() || title.isEmpty()){
                    error("Title and Body of the Message must be filled");
                }

                ((AdfSession)getSession()).getAdfService().addClientForumMessage(title, body, ((AdfSession)getSession()).getUserName(), catname);
                PageParameters param = new PageParameters();
                param.put("category", catname);
                setResponsePage(getPage().getPageClass(), param);
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);

        newMessageBody = new Model();
        TextArea newMessageBodyField = new TextArea("newMessageBody", newMessageBody);
        newMessageBodyField.setRequired(true);
        form.add(newMessageBodyField);

        newMessageTitle = new Model();
        TextField newMessageTitleField = new TextField("newMessageTitle", newMessageTitle);
        newMessageTitleField.setRequired(true);
        form.add(newMessageTitleField);

        add(form);

        MetaDataRoleAuthorizationStrategy.authorize(form, RENDER, "ROLE_client");
    }
}
