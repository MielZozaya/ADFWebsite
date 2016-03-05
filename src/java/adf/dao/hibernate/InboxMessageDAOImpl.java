package adf.dao.hibernate;

import adf.dao.InboxMessageDAO;
import adf.model.InboxMessage;
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
public class InboxMessageDAOImpl extends HibernateDaoSupport implements InboxMessageDAO {

    public List<InboxMessage> getInboxMessageForUser(final String username) {
        return (List<InboxMessage>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session) {
                        Criteria criteria = session.createCriteria(InboxMessage.class);

                        criteria.createAlias("receiver", "rec").add(Expression.eq("rec.username", username));

                        return criteria.addOrder(Order.desc("date")).list();
                    }
                });
    }

    public List<InboxMessage> getInboxMessage() {
        return getHibernateTemplate().find("from InboxMessage order by i.date desc");
    }

    public void save(InboxMessage inboxMessage) {
        getHibernateTemplate().save(inboxMessage);
    }

    public InboxMessage getInboxMessageById(Long id) {
        return (InboxMessage) getHibernateTemplate().get(InboxMessage.class, id);
    }

    public void delete(InboxMessage message) {
        getHibernateTemplate().delete(message);
    }

}
