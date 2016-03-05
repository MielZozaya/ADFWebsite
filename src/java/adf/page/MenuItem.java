package adf.page;

import java.io.Serializable;
import org.apache.wicket.markup.html.link.Link;

/**
 *
 * @author Jeff
 * source taken from http://jeff-schwartz.blogspot.com/2009/03/adventure-with-wicket.html at 20 July 2010
 */

/*
 * An abstraction of an <a> link that has href,
 * description and current (selected) attributes.
 *
 * Can be user for menu and tab items.s
 */
public class MenuItem implements Serializable{

    private Link href;

    public Link getHref(){
        return href;
    }

    public void setHref(Link href){
        this.href = href;
    }

    private String description;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public MenuItem(Link href, String description, boolean selected){
        setHref(href);
        if (description == null) {
            setDescription("");
        } else {
            setDescription(description);
        }
        setSelected(selected);
    }
}
