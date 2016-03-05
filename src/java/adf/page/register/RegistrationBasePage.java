/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.register;

import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath ( path = "Register/BasePage")
public class RegistrationBasePage extends AdfWebPage{

    AdfBorder border;
    public RegistrationBasePage(){
       border = new AdfBorder("adfBorder",11);
       add(border);
    }
}
