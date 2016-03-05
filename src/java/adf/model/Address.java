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
public class Address implements Serializable{
    Long id;
    int version;
    String line1;
    String line2;
    String line3;
    String line4;
    String city;
    String country;
    String postcode;

    public Address(String line1, String line2, String line3, String line4, String city, String country, String postcode) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;

        Address address = (Address) o;

        if (!id.equals(address.id))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    public String toString(){
        StringBuffer sb = new StringBuffer("Address(");
        sb.append("line1=");
        sb.append(line1);
        sb.append(", line2=");
        sb.append(line2);
        sb.append(", line3=");
        sb.append(line3);
        sb.append(", line4=");
        sb.append(line4);
        sb.append(", city=");
        sb.append(city);
        sb.append(", country=");
        sb.append(country);
        sb.append(", postcode=");
        sb.append(postcode);
        sb.append(")");
        return sb.toString();
    }
}
