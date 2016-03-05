/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Professional;

import adf.model.AdfUser;
import adf.page.AdfSession;
import adf.page.SelfHelpSupport.SelfHelpProgramAllAnswers;
import adf.page.User.SendMessage;
import adf.page.register.Registration;
import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Professional/MyClients")
@AuthorizeInstantiation({"ROLE_professional"})
public class MyClients extends ProfessionalBasePage{

    Model invitationEmail = new Model();
    public MyClients(){
        super(MyClients.class);

        List<AdfUser> clientsList = getAdfService().getClientsForProfessional(getAdfSession().getUserName());

        Label noClients = new Label("noClients","You have no clients");
        border.add(noClients);

        WebMarkupContainer clientTable = new WebMarkupContainer("clientTable");
        border.add(clientTable);

        ListView clients = new ListView("clients",clientsList) {

            @Override
            protected void populateItem(ListItem li) {
                AdfUser user = (AdfUser) li.getModelObject();
                final String username = user.getUsername();


                li.add(new Label("username",username));
                Link seeExercises = new Link("exercises") {

                    @Override
                    public void onClick() {
                        setResponsePage(new SelfHelpProgramAllAnswers(username));
                    }
                };

                li.add(seeExercises);

                Link sendMessage = new Link("sendMessage") {

                    @Override
                    public void onClick() {
                        setResponsePage(new SendMessage(username));
                    }
                };

                li.add(sendMessage);

                //Set link visibility
                if(user.isDenyProfessionalSupervising()){
                    seeExercises.setVisible(false);
                }
                if(user.isDenyProfessionalContact()){
                    sendMessage.setVisible(false);
                }
            }
        };

        clientTable.add(clients);

        Form sendInvitation = new Form("sendInvitation"){

            @Override
            protected void onSubmit() {
                String email = (String) invitationEmail.getObject();

                // Get Invitation url
                PageParameters param = new PageParameters();
                param.put("professionalUsername",getAdfSession().getUserName());
                String url = RequestUtils.toAbsolutePath(urlFor(Registration.class,param).toString());

                getAdfService().sendInvitation(getAdfSession().getUserName(), email, url);
                error("Invitation sent");
                invitationEmail.setObject(new String());
            }

        };
        border.add(sendInvitation);
        FeedbackPanel feedback = new FeedbackPanel("feedback");
        sendInvitation.add(feedback);
        
        TextField email = new TextField("email",invitationEmail);
        email.setRequired(true);
        email.add(EmailAddressValidator.getInstance());
        sendInvitation.add(email);

        // Set Visibility of components
        if(clientsList.isEmpty()){
            clientTable.setVisible(false);
        } else {
            noClients.setVisible(false);
        }


    }
    
}
