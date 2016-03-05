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
public class ViewHistory implements Serializable{

    private ViewHistoryId id;
    private int version;
    private Date startDate;
    private Date endDate;
    private String pageUrl;
    private String username;

    public ViewHistory() {
    }

    public ViewHistory(Date startDate, String username) {
        this.startDate = startDate;
        this.username = username;
        id = new ViewHistoryId(startDate, username);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ViewHistoryId getId() {
        return id;
    }

    public void setId(ViewHistoryId id) {
        this.id = id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Date getStartDate() {
        return getId().getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUsername() {
        return getId().getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewHistory)) {
            return false;
        }

        ViewHistory viewHistory = (ViewHistory) o;

        if (!id.equals(viewHistory.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 127;
        }
        return id.hashCode();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("ViewHistory(");
        sb.append("id=");
        sb.append(id);
        sb.append(", starDate=");
        sb.append(startDate);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", pageUrl=");
        sb.append(pageUrl);
        if (username != null) {
            sb.append(", user=");
            sb.append(username);
        }
        sb.append(")");
        return sb.toString();
    }
}
