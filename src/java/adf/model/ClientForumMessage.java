package adf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author miel
 */
public class ClientForumMessage extends ForumMessage implements Serializable {

    public ClientForumMessage(String title, String message, Date date) {
        super(title, message, date);

    }

    public ClientForumMessage() {
    }

    

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof ClientForumMessage))
            return false;

        ClientForumMessage forumMessage = (ClientForumMessage) o;

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
        StringBuffer sb = new StringBuffer("ClientForumMessage(");
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
