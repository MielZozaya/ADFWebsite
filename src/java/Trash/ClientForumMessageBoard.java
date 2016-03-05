package adf.page.Client;

import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import adf.model.ForumMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.basic.Label;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_admin","ROLE_client"})
public class ClientForumMessageBoard extends AdfWebPage{

    Model newMessageBody;
    Model newMessageTitle;

    public ClientForumMessageBoard(final String catname) {

        AdfBorder border = new AdfBorder("adfBorder",3);
        add(border);

        border.add(new Label("threadsHeader", "Threads:"));
        border.add(new TreeTablePanel("loadMessages", (List<ForumMessage>) getAdfService().getClientForumMessagesByCategory(catname)));

        border.add(new Label("newMessage", "New Thread:"));
        Form form = new Form("Message"){
            
            @Override
            protected void onSubmit() {
                String body = (String) newMessageBody.getObject();
                String title = (String) newMessageTitle.getObject();

                if(body.isEmpty() || title.isEmpty()){
                    error("Title and Body of the Message must be filled");
                }

                getAdfService().addClientForumMessage(title, body, getAdfSession().getUserName(), catname);
                setResponsePage(new ClientForumMessageBoard(catname));
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        border.add(feedback);

        newMessageBody = new Model();
        TextArea newMessageBodyField = new TextArea("newMessageBody", newMessageBody);
        newMessageBodyField.setRequired(true);
        form.add(newMessageBodyField);

        newMessageTitle = new Model();
        TextField newMessageTitleField = new TextField("newMessageTitle", newMessageTitle);
        newMessageTitleField.setRequired(true);
        form.add(newMessageTitleField);

        border.add(form);

        MetaDataRoleAuthorizationStrategy.authorize(form, RENDER, "ROLE_client");
    }

}
