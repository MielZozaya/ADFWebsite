/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.Client;

import adf.model.ClientCategory;
import adf.page.AdfSession;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
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
public class ClientCategoriesPanel extends Panel {

    Model newCategoryName;
    Model newCategoryDescription;
    private static final Logger logger = Logger.getLogger(ClientCategoriesPanel.class.getCanonicalName());

    public ClientCategoriesPanel(String id) {
        super(id);

        logger.debug("Getting Client Categories.");
        List<ClientCategory> clientCategory = ((AdfSession) getSession()).getAdfService().getClientCategories();
        logger.debug("Client Categories getted.");

        ListView categories = new ListView("categories", clientCategory) {

            @Override
            protected void populateItem(ListItem li) {
                final ClientCategory cat = (ClientCategory) li.getModelObject();
                li.add(new Link("categoryLink") {

                    @Override
                    public void onClick() {
                        PageParameters param = new PageParameters();
                        param.put("category", cat.getName());
                        setResponsePage(getPage().getClass(), param);
                    }
                }.add(new Label("categoryName", cat.getName())));

                Link deleteCategory = new Link("deleteCategory") {

                    @Override
                    public void onClick() {
                        ((AdfSession) getSession()).getAdfService().deleteClientCategory(cat.getName());
                        setResponsePage(getPage().getClass());
                    }
                };

                if (cat.getName().equals("All")) {
                    deleteCategory.setVisible(false);
                }

                li.add(deleteCategory);

                MetaDataRoleAuthorizationStrategy.authorize(deleteCategory, RENDER, "ROLE_admin");
            }
        };

        add(categories);

        /*Form for adding a category*/
        Form addCategory = new Form("addCategory") {

            @Override
            protected void onSubmit() {
                String catName = (String) newCategoryName.getObject();
                String catDesc = (String) newCategoryDescription.getObject();

                if (catName.isEmpty() || catDesc.isEmpty()) {
                    error("Both category name and description must be filled");
                    return;
                }

                ((AdfSession) getSession()).getAdfService().addClientCategory(catName, catDesc);
                setResponsePage(getPage().getClass());
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        addCategory.add(feedback);

        newCategoryName = new Model();
        TextField newCategoryNameField = new TextField("catName", newCategoryName);
        newCategoryNameField.setRequired(true);
        addCategory.add(newCategoryNameField);

        newCategoryDescription = new Model();
        TextField newCategoryDescField = new TextField("catDesc", newCategoryDescription);
        newCategoryDescField.setRequired(true);
        addCategory.add(newCategoryDescField);

        add(addCategory);

        MetaDataRoleAuthorizationStrategy.authorize(addCategory, RENDER, "ROLE_admin");
    }
}
