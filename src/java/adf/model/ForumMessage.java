/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 *
 * @author miel
 */
public abstract class ForumMessage implements Serializable{
    protected Long id;
    protected int version;
    protected String title;
    protected String message;
    protected Date date;
    protected AdfUser sender;
    protected Set<Category> categories = new HashSet<Category>();
    protected List<ForumMessage> children = new ArrayList<ForumMessage>();
    protected ForumMessage parent;

    public ForumMessage() {
    }

    public ForumMessage(String title, String message, Date date) {
        this.title = title;
        this.message = message;
        this.date = date;
        parent= null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public AdfUser getSender() {
        return sender;
    }

    public void setSender(AdfUser sender) {
        this.sender = sender;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category){
        this.categories.add(category);
        for(ForumMessage o : children){
            o.addCategory(category);
        }
    }

    public void deleteCategory(Category category){
        this.categories.remove(category);
        for(ForumMessage o : children){
            o.deleteCategory(category);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ForumMessage> getChildren() {
        return children;
    }

    public void setChildren(List<ForumMessage> children) {
        this.children = children;
    }

    public void addChild(ForumMessage child){
        child.setParent(this);
        this.children.add(0, child);
        child.setCategories(this.getCategories());
    }

    public ForumMessage getParent() {
        return parent;
    }

    public void setParent(ForumMessage parent) {
        this.parent = parent;
    }

     @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof ForumMessage))
            return false;

        ForumMessage forumMessage = (ForumMessage) o;

        if (!id.equals(forumMessage.id))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    public String toString(){
        StringBuffer sb = new StringBuffer("ForumMessage(");
        sb.append("id=");
        sb.append(id);
        sb.append(", message=");
        sb.append(message);
        sb.append(", date=");
        sb.append(date);
        sb.append(", sender=");
        sb.append(sender.getUsername());
        sb.append(")");
        return sb.toString();
    }

    
}
