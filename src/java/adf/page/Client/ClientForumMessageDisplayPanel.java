/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.Client;

import adf.model.ClientCategory;
import adf.model.ClientForumMessage;
import adf.page.AdfSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_admin", "ROLE_client"})
public class ClientForumMessageDisplayPanel extends Panel {

    Model replyMessageBody;
    Model replyMessageTitle;
    Model editionMessageBody;
    Model editionMessageTitle;
    Model selectedCategory;

    public ClientForumMessageDisplayPanel(String id, final Long messageId) {
        super(id);

        ClientForumMessage message = ((AdfSession) getSession()).getAdfService().getClientForumMessage(messageId);
        add(new Label("title", message.getTitle()));
        add(new MultiLineLabel("body", message.getMessage()));
        add(new Label("author", message.getSender().getNickname()));
        add(new Label("date", message.getDate().toString()));

        add(new Label("threadsHeader", "Replies:"));
        add(new TreeTablePanel("loadMessages", message.getChildren()));

        /*New Reply form*/
        add(new Label("newMessage", "New Thread:"));
        Form form = new Form("Message") {

            @Override
            protected void onSubmit() {
                String body = (String) replyMessageBody.getObject();
                String title = (String) replyMessageTitle.getObject();

                if (body.isEmpty() || title.isEmpty()) {
                    error("Title and Body of the Message must be filled");
                    return;
                }

                ((AdfSession) getSession()).getAdfService().addClientForumMessageReply(title, body, ((AdfSession) getSession()).getUserName(), messageId);
                PageParameters param = new PageParameters();
                param.put("messageId", messageId);
                setResponsePage(getPage().getPageClass(), param);
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);

        replyMessageBody = new Model();
        TextArea newMessageBodyField = new TextArea("newMessageBody", replyMessageBody);
        newMessageBodyField.setRequired(true);
        form.add(newMessageBodyField);

        replyMessageTitle = new Model();
        TextField newMessageTitleField = new TextField("newMessageTitle", replyMessageTitle);
        newMessageTitleField.setRequired(true);
        form.add(newMessageTitleField);

        add(form);

        MetaDataRoleAuthorizationStrategy.authorize(form, RENDER, "ROLE_client");

        /**********************/
        /*Message edition form*/
        Form editionform = new Form("MessageEdition") {

            @Override
            protected void onSubmit() {
                String body = (String) editionMessageBody.getObject();
                String title = (String) editionMessageTitle.getObject();

                if (body.isEmpty() || title.isEmpty()) {
                    error("Title and Body of the Message must be filled");
                    return;
                }

                ((AdfSession) getSession()).getAdfService().editClientForumMessage(title, body, messageId);
                PageParameters param = new PageParameters();
                param.put("messageId", messageId);
                setResponsePage(getPage().getPageClass(), param);
            }
        };

        editionform.add(new Label("editionTitle", "Edit:"));
        FeedbackPanel editionfeedback = new FeedbackPanel("editionfeedback");
        editionform.add(editionfeedback);

        editionMessageBody = new Model();
        TextArea editionMessageBodyField = new TextArea("editionMessageBody", editionMessageBody);
        editionMessageBodyField.setRequired(true);
        editionform.add(editionMessageBodyField);
        editionMessageBody.setObject(message.getMessage());

        editionMessageTitle = new Model();
        TextField editionMessageTitleField = new TextField("editionMessageTitle", editionMessageTitle);
        editionMessageTitleField.setRequired(true);
        editionform.add(editionMessageTitleField);
        editionMessageTitle.setObject(message.getTitle());

        add(editionform);

        MetaDataRoleAuthorizationStrategy.authorize(editionform, RENDER, "ROLE_admin");

        /***************************/
        /*Category Edition*/
        /*Categoies of the message*/
        List<ClientCategory> messageCategories = new ArrayList<ClientCategory>();
        messageCategories.addAll((Collection)message.getCategories());

        Label categoryEditionLabel = new Label("categoryEditionTitle","Edit categories:");
        ListView categories = new ListView("categories", messageCategories) {

            @Override
            protected void populateItem(ListItem li) {
                final ClientCategory cat = (ClientCategory) li.getModelObject();
                li.add(new Label("categoryName", cat.getName()));

                Link deleteCategory = new Link("deleteCategory") {

                    @Override
                    public void onClick() {
                        ((AdfSession) getSession()).getAdfService().deleteCategoryFromClientForumMessage(cat.getName(),messageId);
                        PageParameters param = new PageParameters();
                        param.put("messageId", messageId);
                        setResponsePage(getPage().getPageClass(), param);
                    }
                };

                if (cat.getName().equals("All")) {
                    deleteCategory.setVisible(false);
                }

                li.add(deleteCategory);
            }
        };

        add(categoryEditionLabel);
        add(categories);

        MetaDataRoleAuthorizationStrategy.authorize(categoryEditionLabel, RENDER, "ROLE_admin");
        MetaDataRoleAuthorizationStrategy.authorize(categories, RENDER, "ROLE_admin");
        /**************************/

        /*Add Categoy form*/
        Form categoryEditionform = new Form("CategoryEdition") {

            @Override
            protected void onSubmit() {
                ClientCategory cat = (ClientCategory) selectedCategory.getObject();

                if (cat == null) {
                    error("A category must be selected");
                    return;
                }

                ((AdfSession) getSession()).getAdfService().addCategoryToClientForumMessage(cat.getName(), messageId);
                PageParameters param = new PageParameters();
                param.put("messageId", messageId);
                setResponsePage(getPage().getPageClass(), param);
            }
        };

        FeedbackPanel categoryEditionFeedback = new FeedbackPanel("categoryEditionFeedback");
        categoryEditionform.add(categoryEditionFeedback);

        selectedCategory = new Model();
        List<ClientCategory> availableCategories = ((AdfSession) getSession()).getAdfService().getClientCategories();
        availableCategories.removeAll(messageCategories);
        ChoiceRenderer renderer = new ChoiceRenderer("name", "name");

        DropDownChoice choice = new DropDownChoice("categoryList", selectedCategory, availableCategories, renderer);
        choice.setRequired(true);
        categoryEditionform.add(choice);

        add(categoryEditionform);

        MetaDataRoleAuthorizationStrategy.authorize(categoryEditionform, RENDER, "ROLE_admin");
        /************************/
        /*Set Category edition Visibility to false if not root message*/
        if(message.getParent() != null){
            categoryEditionLabel.setVisible(false);
            categories.setVisible(false);
            categoryEditionform.setVisible(false);
        }
    }
}