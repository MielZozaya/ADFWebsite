/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import java.io.Serializable;

/**
 *
 * @author miel
 */
public class Demographics implements Serializable{
    private Long id;
    private int version;
    private String gender;
    private int age;
    private String occupation;
    private String ethnicity;
    private String religion;
    private String country;
    private String problemDuration;
    private String typeOfProblem;
    private String isRelativeMisUsing;
    private String relativesGender;
    private String convivienceDuration;
    private String relationWithAdict;
    private int relativesAge;
    private String adictionChangeIn3Months;

    public Demographics() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public String getAdictionChangeIn3Months() {
        return adictionChangeIn3Months;
    }

    public void setAdictionChangeIn3Months(String adictionChangeIn3Months) {
        this.adictionChangeIn3Months = adictionChangeIn3Months;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getConvivienceDuration() {
        return convivienceDuration;
    }

    public void setConvivienceDuration(String convivienceDuration) {
        this.convivienceDuration = convivienceDuration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsRelativeMisUsing() {
        return isRelativeMisUsing;
    }

    public void setIsRelativeMisUsing(String isRelativeMisUsing) {
        this.isRelativeMisUsing = isRelativeMisUsing;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getProblemDuration() {
        return problemDuration;
    }

    public void setProblemDuration(String problemDuration) {
        this.problemDuration = problemDuration;
    }

    public String getRelationWithAdict() {
        return relationWithAdict;
    }

    public void setRelationWithAdict(String relationWithAdict) {
        this.relationWithAdict = relationWithAdict;
    }

    public int getRelativesAge() {
        return relativesAge;
    }

    public void setRelativesAge(int relativesAge) {
        this.relativesAge = relativesAge;
    }

    public String getRelativesGender() {
        return relativesGender;
    }

    public void setRelativesGender(String relativesGender) {
        this.relativesGender = relativesGender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTypeOfProblem() {
        return typeOfProblem;
    }

    public void setTypeOfProblem(String typeOfProblem) {
        this.typeOfProblem = typeOfProblem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Demographics other = (Demographics) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
