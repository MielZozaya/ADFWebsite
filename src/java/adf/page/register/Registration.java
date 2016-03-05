/*
 * The captcha code is taken from: http://www.wicket-library.com/wicket-examples/captcha
 */
package adf.page.register;

import adf.model.RegistrationInformation;
import adf.model.ProfessionalLoginPetitionForm;
import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.captcha.CaptchaImageResource;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountQueryString;

/**
 *
 * @author miel
 */
@MountPath (path = "Registration")
@MountQueryString
public class Registration extends RegistrationBasePage {

    Model usernameModel = new Model();
    Model emailModel = new Model();
    Model reEmailModel = new Model();
    Model telephoneModel = new Model();
    CaptchaImageResource captchaImageResource;
    /** Random captcha password to match against. */
    private final String imagePass = randomString(6, 8);
    private final ValueMap properties = new ValueMap();
    
    // parameter recieved if the client to be registered has to be linked to a professional
    private String professionalUsername = null;
    
    // parameter recieved if the registration is a professional level registration
    private String petitionId = null;

    public Registration(){
        this(null);
    }

    public Registration(PageParameters params) {

        // Get Page parameters if there is any
        if(params != null){
            if(params.getString("professionalUsername") != null){
                professionalUsername = params.getString("professionalUsername");
            }
            else if(params.getString("petitionId") != null){
                petitionId = params.getString("petitionId");
            }
        }

        // If the both parameters are recieved an error must be occurred
        if(professionalUsername != null && petitionId != null){
            setResponsePage(RegistrationError.class);
        }



        Form registrationForm = new Form("registrationForm") {

            @Override
            protected void onSubmit() {
                String username = (String) usernameModel.getObject();
                String email = (String) emailModel.getObject();
                String reEmail = (String) reEmailModel.getObject();
                String telephone = (String) telephoneModel.getObject();

                if(!email.equals(reEmail)){
                    error("The two emails entered are different. Please try again\n");
                    return;
                }

                if (!imagePass.equals(getPassword())) {
                    error("Captcha text '" + getPassword() + "' is wrong. Please try again\n");
                    // force redrawing
                    captchaImageResource.invalidate();
                } else {
                    // Captcha entered correctly

                    //Check that the username is not already in use
                    if(getAdfService().isUserRegistered(username)){
                        error("Username already in use. Please choose another one.");
                        return;
                    }

                    //Initiate registration in the session
                    getAdfSession().initiateRegistration(username, email, telephone);

                    // If the clients is to be referenced to a professional
                    //  save the professional reference
                    if(professionalUsername != null){
                        RegistrationInformation registrationInformation = getAdfSession().getRegistrationInformation();
                        registrationInformation.setProfessionalUsername(professionalUsername);
                        getAdfSession().setRegistrationInformation(registrationInformation);
                    // If the new user is going to be professional level registration
                    } else if(petitionId != null){
                        RegistrationInformation registrationInformation = getAdfSession().getRegistrationInformation();
                        registrationInformation.setProfessionalPetitionId(petitionId);
                        getAdfSession().setRegistrationInformation(registrationInformation);
                    }

                    // Direct user to the next step on the registration
                    setResponsePage(DemographicsPage.class);
                }
            }
        };

        border.add(registrationForm);

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        registrationForm.add(feedback);

        captchaImageResource = new CaptchaImageResource(imagePass);
        registrationForm.add(new Image("captchaImage", captchaImageResource));
        registrationForm.add(new RequiredTextField<String>("password", new PropertyModel<String>(properties, "password")) {

            @Override
            protected final void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                // clear the field after each render
                tag.put("value", "");
            }
        });

        TextField<String> username = new TextField<String>("username",usernameModel);
        username.setRequired(true);
        registrationForm.add(username);

        TextField<String> email = new TextField<String>("email",emailModel);
        email.setRequired(true);
        email.add(EmailAddressValidator.getInstance());
        registrationForm.add(email);

        TextField<String> reEmail = new TextField<String>("reEmail",reEmailModel);
        reEmail.setRequired(true);
        reEmail.add(EmailAddressValidator.getInstance());
        registrationForm.add(reEmail);

        TextField<String> telephone = new TextField<String>("telephone",telephoneModel);
        telephone.setRequired(true);
        registrationForm.add(telephone);

        // set Components values and enable
        // Get the petition form if the petition id is not null
        if(petitionId != null){
            ProfessionalLoginPetitionForm petition = getAdfService().getProfessionalLoginPetitionForm(petitionId);
            if(!petition.isAcceptedPetition()){
                setResponsePage(RegistrationError.class);
            }
            email.setModelObject(petition.getEmail());
            reEmail.setModelObject(petition.getEmail());
            email.setEnabled(false);
            reEmail.setEnabled(false);
        }

    }

    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    private static String randomString(int min, int max) {
        int num = randomInt(min, max);
        byte b[] = new byte[num];
        for (int i = 0; i < num; i++) {
            b[i] = (byte) randomInt('a', 'z');
        }
        return new String(b);
    }

    private String getPassword() {
        return properties.getString("password");
    }
}
