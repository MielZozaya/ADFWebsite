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
public class InboxMessage implements Serializable {

    private Long id;
    private int version;
    private String message;
    private Date date;
    private AdfUser sender;
    private AdfUser receiver;

    public InboxMessage() {
    }

    public InboxMessage(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public AdfUser getReceiver() {
        return receiver;
    }

    public void setReceiver(AdfUser receiver) {
        this.receiver = receiver;
    }
    
    public void addReceiver(AdfUser receiver) {
        setReceiver(receiver);
        receiver.addRecievedMessage(this);
    }

    public AdfUser getSender() {
        return sender;
    }

    public void setSender(AdfUser sender) {
        this.sender = sender;
    }

    public void addSender(AdfUser sender) {
        setSender(sender);
        if (sender != null) {
            sender.addSentMessage(this);
        }
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InboxMessage)) {
            return false;
        }

        InboxMessage inboxMessage = (InboxMessage) o;

        if (!id.equals(inboxMessage.id)) {
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
        StringBuffer sb = new StringBuffer("InboxMessage(");
        sb.append("id=");
        sb.append(id);
        sb.append(", message=");
        sb.append(message);
        sb.append(", date=");
        sb.append(date);
        if (sender != null) {
            sb.append(", sender=");
            sb.append(sender.getUsername());
        }
        if (receiver != null) {
            sb.append(", receiver=");
            sb.append(receiver.getUsername());
        }
        sb.append(")");
        return sb.toString();
    }
}
