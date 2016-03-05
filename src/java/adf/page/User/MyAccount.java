/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.User;

import adf.model.AdfUser;
import adf.page.AdfBorder;
import adf.page.AdfWebPage;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author miel
 */
@MountPath(path = "MyAccount")
@AuthorizeInstantiation({"ROLE_client", "ROLE_professional","ROLE_admin"})
public class MyAccount extends AdfWebPage {

    Model oldPasswordModel = new Model();
    Model newPasswordModel = new Model();
    Model reEnterNewPasswordModel = new Model();
    Model nicknameModel = new Model();

    public MyAccount() {
        AdfBorder border = new AdfBorder("adfBorder", 3);
        add(border);

        // Get AdfUser
        final AdfUser user = getAdfService().getAdfUser(getAdfSession().getUserName());

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        border.add(feedback);
        // Add Change password form
        Form passwordChangeForm = new Form("passwordChange") {

            @Override
            protected void onSubmit() {
                String oldPassword = (String) oldPasswordModel.getObject();
                String newPassword = (String) newPasswordModel.getObject();
                String reEnterNewPassword = (String) reEnterNewPasswordModel.getObject();

                if(!reEnterNewPassword.equals(newPassword)){
                    error("New Passwords do not match.");
                    return;
                }

                if (getAdfService().changePassword(oldPassword, newPassword, getAdfSession().getUserName())) {
                    error("Your password has been chaged.");
                    oldPasswordModel.setObject(new String());
                    newPasswordModel.setObject(new String());
                } else {
                    error("Incorrect Old Password");
                }
            }
        };

        border.add(passwordChangeForm);

        PasswordTextField oldpassword = new PasswordTextField("oldPassword", oldPasswordModel);
        oldpassword.setRequired(true);
        passwordChangeForm.add(oldpassword);

        PasswordTextField newPassword = new PasswordTextField("newPassword", newPasswordModel);
        newPassword.setRequired(true);
        passwordChangeForm.add(newPassword);

        PasswordTextField reEnterNewPassword = new PasswordTextField("reEnterNewPassword", reEnterNewPasswordModel);
        reEnterNewPassword.setRequired(true);
        passwordChangeForm.add(reEnterNewPassword);

        // Nickname Change form
        Form nicknameChangeForm = new Form("nicknameChange") {

            @Override
            protected void onSubmit() {
                String nickname = (String) nicknameModel.getObject();
                user.setNickname(nickname);
                getAdfService().updateAdfUser(user);
                setResponsePage(MyAccount.class);
            }
        };
        border.add(nicknameChangeForm);

        TextField nickname = new TextField("nickname", nicknameModel);
        nickname.setRequired(true);
        nickname.setModelObject(user.getNickname());
        nicknameChangeForm.add(nickname);

        // Set privacy options
        WebMarkupContainer container = new WebMarkupContainer("supervisorcontainer");
        border.add(container);
        CheckBox denyContact = new CheckBox("denyContact", new Model()) {

            @Override
            protected void onSelectionChanged(Object newSelection) {
                boolean denyContact = ((Boolean) newSelection).booleanValue();
                user.setDenyProfessionalContact(denyContact);
                getAdfService().updateAdfUser(user);
                setResponsePage(MyAccount.class);
            }

            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
        };
        denyContact.setModelObject(new Boolean(user.isDenyProfessionalContact()));
        container.add(denyContact);

        CheckBox denySupervising = new CheckBox("denySupervising", new Model()) {

            @Override
            protected void onSelectionChanged(Object newSelection) {
                boolean denySupervising = ((Boolean) newSelection).booleanValue();
                user.setDenyProfessionalSupervising(denySupervising);
                getAdfService().updateAdfUser(user);
                setResponsePage(MyAccount.class);
            }

            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
        };
        denySupervising.setModelObject(new Boolean(user.isDenyProfessionalSupervising()));
        container.add(denySupervising);

        if(user.getProfessional() == null){
            container.setVisible(false);
        }
    }
}

