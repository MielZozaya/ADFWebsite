package adf.dao;


import adf.model.Category;
import adf.model.ForumMessage;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ForumMessageDAO
{
    public List<ForumMessage> getForumMessages();

    public ForumMessage getForumMessage(Long messageId);

    public List<ForumMessage> getForumMessagesByCategory(Category category);

    public void save(ForumMessage forumMessage);

    public void update(ForumMessage forumMessage);
}
