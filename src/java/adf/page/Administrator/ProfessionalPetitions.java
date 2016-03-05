/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.Administrator;

import adf.model.ProfessionalLoginPetitionForm;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath (path = "Administrator/ProfessionalPetitions")
@AuthorizeInstantiation({"ROLE_admin"})
public class ProfessionalPetitions extends AdministrationBasePage{

    public ProfessionalPetitions() {
        super(ProfessionalPetitions.class);

        List<ProfessionalLoginPetitionForm> fullpetitionList = getAdfService().getProfessionalLoginPetitionForms();

        List<ProfessionalLoginPetitionForm> petitionList = new ArrayList<ProfessionalLoginPetitionForm>();
        // Take out the already accepted petitions
        for(ProfessionalLoginPetitionForm petition: fullpetitionList){
            if(!petition.isAcceptedPetition()){
                petitionList.add(petition);
            }
        }

        ListView petitions = new ListView("petitions",petitionList){

            @Override
            protected void populateItem(ListItem li) {
                final ProfessionalLoginPetitionForm petition = (ProfessionalLoginPetitionForm)li.getModelObject();
                li.add(new Label("date",petition.getCreationDate().toString()));
                li.add(new Label("name",petition.getName()));
                li.add(new Label("middleName",petition.getMiddleName()));
                li.add(new Label("surname",petition.getSurname()));
                li.add(new Link("seePetition") {

                    @Override
                    public void onClick() {
                        setResponsePage(new SeePetition(petition.getId()));
                    }
                });
            }

        };

        border.add(petitions);
    }

}
