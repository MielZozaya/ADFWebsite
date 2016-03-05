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
public class WebsiteFeedback implements Serializable{

    private Long id;
    private int version;
    private String text;
    private int stars;
    private AdfUser user;

    public WebsiteFeedback() {
    }

    public WebsiteFeedback(String text, int stars) {
        this.text = text;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public AdfUser getUser() {
        return user;
    }

    public void setUser(AdfUser user) {
        this.user = user;
    }

     @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof WebsiteFeedback))
            return false;

        WebsiteFeedback feedback = (WebsiteFeedback) o;

        if (!id.equals(feedback.id))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("WebsiteFeedback(");
        sb.append("id=");
        sb.append(id);
        sb.append(", text=");
        sb.append(text);
        sb.append(", stars=");
        sb.append(stars);
        sb.append(")");
        return sb.toString();
    }
}
