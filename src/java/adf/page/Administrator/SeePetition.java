/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Administrator;

import adf.page.register.ProfessionalLoginPetitionFormPanel;
import adf.page.register.Registration;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.RequestUtils;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Administrator/SeePetition")
@AuthorizeInstantiation({"ROLE_admin"})
public class SeePetition extends AdministrationBasePage{

    public SeePetition(final String petitionId) {
        super(ProfessionalPetitions.class);

        border.add(new ProfessionalLoginPetitionFormPanel("petitionPanel", petitionId));
        border.add(new Link("accept") {

            @Override
            public void onClick() {

                // Ger url for the registration
                PageParameters param = new PageParameters();
                param.put("petitionId",petitionId);
                String url = RequestUtils.toAbsolutePath(urlFor(Registration.class,param).toString());
                
                getAdfService().acceptProfessionalLoginPetition(petitionId, url);
                setResponsePage(ProfessionalPetitions.class);
            }
        });
        border.add(new Link("decline") {

            @Override
            public void onClick() {
                getAdfService().declineProfessionalLoginPetition(petitionId);
                setResponsePage(ProfessionalPetitions.class);
            }
        });
    }

}
