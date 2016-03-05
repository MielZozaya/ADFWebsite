/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.dao.hibernate;

import adf.dao.ViewHistoryDAO;
import adf.model.ViewHistory;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ViewHistoryDAOImpl extends HibernateDaoSupport implements ViewHistoryDAO{

    public void save(ViewHistory viewHistory) {
        getHibernateTemplate().save(viewHistory);
    }

    public List<ViewHistory> getViewHistories() {
        return getHibernateTemplate().find("from ViewHistory");
    }

}
