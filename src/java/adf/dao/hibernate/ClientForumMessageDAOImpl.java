package adf.dao.hibernate;

import adf.dao.ForumMessageDAO;
import adf.model.Category;
import adf.model.ClientCategory;
import adf.model.ClientForumMessage;
import adf.model.ForumMessage;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ClientForumMessageDAOImpl extends HibernateDaoSupport implements ForumMessageDAO {

    public List<ForumMessage> getForumMessages() {
        return (List<ForumMessage>) getHibernateTemplate().find("from ClientForumMessage");
    }

    public ForumMessage getForumMessage(Long messageId){
        return (ForumMessage) getHibernateTemplate().get(ClientForumMessage.class, messageId);
    }

    public List<ForumMessage> getForumMessagesByCategory(final Category category) {
        return (List<ForumMessage>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session) {
                        Criteria criteria = session.createCriteria(ClientForumMessage.class);

                        criteria.add(Expression.isNull("parent"));
                        
                        if (category != null) {
                            criteria.createAlias("categories", "cat").add(Expression.eq("cat.name", ((ClientCategory)category).getName()));
                        }

                        return criteria.addOrder(Order.desc("date")).list();
                    }
                });
    }

    public void save(ForumMessage forumMessage) {
        getHibernateTemplate().save((ClientForumMessage) forumMessage);
    }

    public void update(ForumMessage forumMessage) {
        getHibernateTemplate().update((ClientForumMessage) forumMessage);
    }
}
