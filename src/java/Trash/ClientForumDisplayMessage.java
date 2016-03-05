/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Client;

import adf.model.ClientForumMessage;
import adf.model.ForumMessage;
import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
public class ClientForumDisplayMessage extends AdfWebPage{

    Model replyMessageBody;
    Model replyMessageTitle;

    public ClientForumDisplayMessage(final Long messageId) {
        AdfBorder border = new AdfBorder("adfBorder",3);
        add(border);

        ClientForumMessage message = getAdfService().getClientForumMessage(messageId);
        border.add(new Label("title", message.getTitle()));
        border.add(new Label("body", message.getMessage()));
        border.add(new Label("author", message.getSender().getNickname()));
        border.add(new Label("date", message.getDate().toString()));

        border.add(new Label("threadsHeader", "Replies:"));
        border.add(new TreeTablePanel("loadMessages", message.getChildren()));

        border.add(new Label("newMessage", "New Thread:"));
        Form form = new Form("Message"){

            @Override
            protected void onSubmit() {
                String body = (String) replyMessageBody.getObject();
                String title = (String) replyMessageTitle.getObject();

                if(body.isEmpty() || title.isEmpty()){
                    error("Title and Body of the Message must be filled");
                    return;
                }

                getAdfService().addClientForumMessageReply(title, body, getAdfSession().getUserName(), messageId);
                setResponsePage(new ClientForumDisplayMessage(messageId));
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        border.add(feedback);

        replyMessageBody = new Model();
        TextArea newMessageBodyField = new TextArea("newMessageBody", replyMessageBody);
        newMessageBodyField.setRequired(true);
        form.add(newMessageBodyField);

        replyMessageTitle = new Model();
        TextField newMessageTitleField = new TextField("newMessageTitle", replyMessageTitle);
        newMessageTitleField.setRequired(true);
        form.add(newMessageTitleField);

        border.add(form);

        MetaDataRoleAuthorizationStrategy.authorize(form, RENDER, "ROLE_client");
    }


}
