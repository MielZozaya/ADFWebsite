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
@MountPath ( path = "Register/PetitionForm")
public class ProfessionalLoginPetitionFormPage extends RegistrationBasePage{

    public ProfessionalLoginPetitionFormPage() {
        border.add(new ProfessionalLoginPetitionFormPanel("petitionPanel",null));
    }

}
