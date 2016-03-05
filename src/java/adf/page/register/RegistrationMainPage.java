package adf.page.register;

import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath ( path = "Register/Registration")
public class RegistrationMainPage extends RegistrationBasePage{

    public RegistrationMainPage(){
        border.add(new Link("clientRegister") {

            @Override
            public void onClick() {
                setResponsePage(Registration.class);
            }
        });
        
        border.add(new Link("professionalPetition"){

            @Override
            public void onClick() {
                setResponsePage(ProfessionalLoginPetitionFormPage.class);
            }
        });
        
    }

}
