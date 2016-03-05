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
public class ViewHistoryId implements Serializable{

    private Date startDate;
    private String username;

    public ViewHistoryId() {
    }

    public ViewHistoryId(Date startDate, String username) {
        this.startDate = startDate;
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewHistoryId)) {
            return false;
        }

        ViewHistoryId viewHistoryId = (ViewHistoryId) o;

        if (!startDate.equals(viewHistoryId.startDate)) {
            return false;
        }

        if (!username.equals(viewHistoryId.username)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 127;

        if (startDate != null) {
            result += 11*startDate.hashCode();
        }

        if (username != null) {
           result += 7*username.hashCode();
        }

        return result;
    }
}
