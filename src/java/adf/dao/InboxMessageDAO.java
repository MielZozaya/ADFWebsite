package adf.dao;

import adf.model.InboxMessage;
import java.util.List;


public interface InboxMessageDAO {
    public List<InboxMessage> getInboxMessageForUser(String username);

    public List<InboxMessage> getInboxMessage();

    public void save(InboxMessage inboxMessage);

    public InboxMessage getInboxMessageById(Long id);

    public void delete(InboxMessage message);
}
