/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.User;

import adf.model.AdfUser;
import adf.model.InboxMessage;
import adf.page.AdfWebPage;
import adf.page.AdfBorder;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path = "user/Inbox")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class InboxPage extends AdfWebPage{

    public InboxPage() {
        AdfBorder border = new AdfBorder("adfBorder",7);
        add(border);

        final AdfUser user = getAdfService().getAdfUser(getAdfSession().getUserName());

        WebMarkupContainer messageListContainer = new WebMarkupContainer("messageListContainer");
        List<InboxMessage> messageList = getAdfService().getInboxMessages(getAdfSession().getUserName());
        messageListContainer.add(new ListView("messages", messageList) {

            @Override
            protected void populateItem(ListItem li) {
                final InboxMessage message = (InboxMessage) li.getModelObject();
                li.add(new MultiLineLabel("message",message.getMessage()));
                li.add(new Label("date",message.getDate().toString()));
                li.add(new Label("sender",message.getSender()==null? "System":message.getSender().getUsername()));
                li.add(new Link("delete") {

                    @Override
                    public void onClick() {
                        getAdfService().deleteInboxMessage(message.getId(), getAdfSession().getUserName());
                        setResponsePage(InboxPage.class);
                    }
                });
            }
        });

        border.add(messageListContainer);

        Label noMessagesLabel = new Label("noMessages","You have no messages");
        border.add(noMessagesLabel);

        WebMarkupContainer professionalContactContainer = new WebMarkupContainer("professionalContactContainer");
        border.add(professionalContactContainer);

        Link professionalContact = new Link("professionalContact"){

            @Override
            public void onClick() {
                setResponsePage(new SendMessage(user.getProfessional().getUsername()));
            }

        };

        professionalContactContainer.add(professionalContact);

        // Set components visibility
        if(messageList == null || messageList.isEmpty()){
            messageListContainer.setVisible(false);
        } else {
            noMessagesLabel.setVisible(false);
        }

        if(user.getProfessional() == null){
            professionalContactContainer.setVisible(false);
        }

    }

}
