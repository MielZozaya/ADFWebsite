/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.User;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path = "user/SendAMessage")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SendMessage extends AdfWebPage{

    Model messageBody = new Model();

    public SendMessage (final String username){
        AdfBorder border = new AdfBorder("adfBorder",4);
        add(border);

        Form form = new Form("Message"){

            @Override
            protected void onSubmit() {
                String body = (String) messageBody.getObject();

                if(body.isEmpty()){
                    error("Body of the Message must be filled");
                }

                if(getAdfService().addInboxMessage(body, username, getAdfSession().getUserName())){
                    setResponsePage(new SendMessageResponse(true));
                }
                else{
                    setResponsePage(new SendMessageResponse(false));
                }
            }
        };

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        border.add(feedback);

        messageBody = new Model();
        TextArea messageBodyField = new TextArea("messageBody", messageBody);
        messageBodyField.setRequired(true);
        form.add(messageBodyField);

        border.add(form);
    }
}
