/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.dao.hibernate;

import adf.dao.ToolDAO;
import adf.model.Tool;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ToolDAOImpl extends HibernateDaoSupport implements ToolDAO{

    public List<Tool> getTools() {
        return getHibernateTemplate().find("from Tool");
    }

    public void save(Tool tool) {
        getHibernateTemplate().save(tool);
    }

    public Tool getTool(Long id) {
        return (Tool) getHibernateTemplate().load(Tool.class, id);
    }

    public void delete(Tool tool) {
        getHibernateTemplate().delete(tool);
    }

}
