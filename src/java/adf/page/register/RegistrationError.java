/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.register;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath ( path = "Register/Error")
public class RegistrationError extends RegistrationBasePage{

    public RegistrationError(){
        this(null);
    }

    public RegistrationError(String error){
        WebMarkupContainer container = new WebMarkupContainer("container");
        border.add(container);
        container.add(new Label("errorCause",error));

        // set container visibility
        if(error == null){
            container.setVisible(false);
        }
    }
}
