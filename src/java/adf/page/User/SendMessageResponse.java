/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.User;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path = "user/Response")
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class SendMessageResponse extends AdfWebPage{

    public SendMessageResponse(boolean succeded) {
        AdfBorder border = new AdfBorder("adfBorder",4);
        add(border);

        if(succeded){
            border.add(new Label("message","Your message was properly sent"));
        }else{
            border.add(new Label("message","Your message could not be sended. \n May be that you have no permisions to send a message to the recipient."));
        }
    }

}
