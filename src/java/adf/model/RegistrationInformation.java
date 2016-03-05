/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;


import adf.model.Demographics;

/**
 *
 * @author miel
 */
public class RegistrationInformation {
    private String username;
    private String email;
    private String telephone;
    private Demographics demographics;
    private String professionalUsername;
    private String professionalPetitionId;

    public RegistrationInformation() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    public String getProfessionalPetitionId() {
        return professionalPetitionId;
    }

    public void setProfessionalPetitionId(String professionalPetitionId) {
        this.professionalPetitionId = professionalPetitionId;
    }

    public String getProfessionalUsername() {
        return professionalUsername;
    }

    public void setProfessionalUsername(String professionalUsername) {
        this.professionalUsername = professionalUsername;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

   
}
