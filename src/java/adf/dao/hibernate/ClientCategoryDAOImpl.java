package adf.dao.hibernate;

import adf.dao.CategoryDAO;
import adf.model.Category;
import adf.model.ClientCategory;
import adf.model.ClientForumMessage;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ClientCategoryDAOImpl extends HibernateDaoSupport implements CategoryDAO {

    public Category loadCategoryByName(String name) {
        return (Category) getHibernateTemplate().get(ClientCategory.class, name);
    }

    public List<Category> getCategory() {
        return (List<Category>) getHibernateTemplate().find("from ClientCategory");
    }

    public void save(Category category) {
        getHibernateTemplate().save((ClientCategory) category);
    }

    public void delete(Category category) {
        ClientForumMessage example = new ClientForumMessage();
        example.addCategory(category);
        List<ClientForumMessage> forumMessageList = getHibernateTemplate().findByExample(example);
        for (ClientForumMessage message: forumMessageList){
            message.deleteCategory(category);
        }
        getHibernateTemplate().delete((ClientCategory) category);
    }
}
