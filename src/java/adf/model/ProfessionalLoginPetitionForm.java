/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author miel
 */
public class ProfessionalLoginPetitionForm implements Serializable{

    private int version;
    private String email;

    private String name;
    private String middleName;
    private String surname;
    private String telephoneNumber;
    private String organization;
    private String positionHold;
    private String personalStatement;
    private Date creationDate;
    private boolean acceptedPetition = false;

    public ProfessionalLoginPetitionForm() {
    }

    public String getId(){
        return getEmail();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPersonalStatement() {
        return personalStatement;
    }

    public void setPersonalStatement(String personalStatement) {
        this.personalStatement = personalStatement;
    }

    public String getPositionHold() {
        return positionHold;
    }

    public void setPositionHold(String positionHold) {
        this.positionHold = positionHold;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAcceptedPetition() {
        return acceptedPetition;
    }

    public void setAcceptedPetition(boolean acceptedPetition) {
        this.acceptedPetition = acceptedPetition;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfessionalLoginPetitionForm other = (ProfessionalLoginPetitionForm) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }


}
