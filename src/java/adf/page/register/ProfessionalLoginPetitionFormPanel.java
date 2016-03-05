/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.register;

import adf.model.ProfessionalLoginPetitionForm;
import adf.page.AdfSession;
import adf.page.Common.Home;
import java.util.Date;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

/**
 *
 * @author miel
 */

public class ProfessionalLoginPetitionFormPanel extends Panel{

    private Model emailModel = new Model();
    private Model nameModel = new Model();
    private Model middleNameModel = new Model();
    private Model surnameModel = new Model();
    private Model telephoneNumberModel = new Model();
    private Model organizationModel = new Model();
    private Model positionHoldModel = new Model();
    private Model personalStatementModel = new Model();

    public ProfessionalLoginPetitionFormPanel(String id, String petitionId) {
        super(id);

        ProfessionalLoginPetitionForm petition;

        if(petitionId != null){
            petition = ((AdfSession)getSession()).getAdfService().getProfessionalLoginPetitionForm(petitionId);
        } else {
            petition = new ProfessionalLoginPetitionForm();
        }

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);

        Form form = new Form("form");
        add(form);

        Button submitButton = new Button("submitButton"){

            @Override
            public void onSubmit() {
                ProfessionalLoginPetitionForm petition = new ProfessionalLoginPetitionForm();
                petition.setName((String)nameModel.getObject());
                petition.setMiddleName((String)middleNameModel.getObject());
                petition.setSurname((String)surnameModel.getObject());
                petition.setEmail((String)emailModel.getObject());
                petition.setTelephoneNumber((String)telephoneNumberModel.getObject());
                petition.setOrganization((String)organizationModel.getObject());
                petition.setPositionHold((String)positionHoldModel.getObject());
                petition.setPersonalStatement((String)personalStatementModel.getObject());
                petition.setCreationDate(new Date(System.currentTimeMillis()));

                if(((AdfSession)getSession()).getAdfService().addProfessionalLoginPetitionForm(petition)){
                   setResponsePage(Home.class);
                }
                else {
                    error("You already sent a petition from this email");
                }
                
            }
        };
        form.add(submitButton);

        TextField name = new TextField("name", nameModel);
        name.setRequired(true);
        form.add(name);

        TextField middleName = new TextField("middleName", middleNameModel);
        middleName.setRequired(false);
        form.add(middleName);

        TextField surname = new TextField("surname", surnameModel);
        surname.setRequired(true);
        form.add(surname);

        TextField email = new TextField("email", emailModel);
        email.setRequired(true);
        email.add(EmailAddressValidator.getInstance());
        form.add(email);

        TextField telephoneNumber = new TextField("telephoneNumber", telephoneNumberModel);
        telephoneNumber.setRequired(true);
        form.add(telephoneNumber);

        TextField organization = new TextField("organization", organizationModel);
        organization.setRequired(true);
        form.add(organization);

        TextField positionHold = new TextField("positionHold", positionHoldModel);
        positionHold.setRequired(true);
        form.add(positionHold);

        TextArea personalStatement = new TextArea("personalStatement", personalStatementModel);
        personalStatement.setRequired(true);
        form.add(personalStatement);

        // Set visibility, editability and content
        if(petitionId != null){
            // set components content
            name.setModelObject(petition.getName());
            middleName.setModelObject(petition.getMiddleName());
            surname.setModelObject(petition.getSurname());
            email.setModelObject(petition.getEmail());
            telephoneNumber.setModelObject(petition.getTelephoneNumber());
            organization.setModelObject(petition.getOrganization());
            positionHold.setModelObject(petition.getPositionHold());
            personalStatement.setModelObject(petition.getPersonalStatement());

            // set enabled false
            name.setEnabled(false);
            middleName.setEnabled(false);
            surname.setEnabled(false);
            email.setEnabled(false);
            telephoneNumber.setEnabled(false);
            organization.setEnabled(false);
            positionHold.setEnabled(false);
            personalStatement.setEnabled(false);

            // set submintButton visibility
            submitButton.setVisible(false);
        }
    }

}
