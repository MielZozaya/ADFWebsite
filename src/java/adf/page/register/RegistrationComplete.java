/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.register;

import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath ( path = "Register/Complete")
public class RegistrationComplete extends RegistrationBasePage{

    public RegistrationComplete(){
        if(getAdfSession().endRegistration()){

        } else {
            setResponsePage(RegistrationError.class);
        }

    }
}
