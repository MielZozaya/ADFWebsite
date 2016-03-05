// ID: 1054920
package adf.dao.hibernate;

import adf.dao.DatabaseDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;

/**
 * User: aps
 * Date: 14-Feb-2008
 * Time: 04:16:10
 */
public class DatabaseDAOImpl extends HibernateDaoSupport implements DatabaseDAO
{
    private LocalSessionFactoryBean lsfb;

    public LocalSessionFactoryBean getLsfb()
    {
        return lsfb;
    }

    public void setLsfb(LocalSessionFactoryBean lsfb)
    {
        this.lsfb = lsfb;
    }

    // This is a special method to get around the lack in Spring's hibernate support for
    // including schema creation operations in the same transaction as other operations
    public void createDB()
    {
        final Configuration cfg = lsfb.getConfiguration();
        getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        final Dialect dialect = Dialect.getDialect(cfg.getProperties());
                        final String[] sql = cfg.generateSchemaCreationScript(dialect);
                        for (String aSql : sql)
                        {
                            logger.debug("Executing schema statement: " + aSql);
                            session.createSQLQuery(aSql).executeUpdate();
                        }
                        return null;
                    }
                }
        );
    }

    // This is a special method to get around the lack in Spring's hibernate support for
    // including schema destruction operations in the same transaction as other operations
    public void destroyDB()
    {
        final Configuration cfg = lsfb.getConfiguration();
        getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        final Dialect dialect = Dialect.getDialect(cfg.getProperties());
                        final String[] sql = cfg.generateDropSchemaScript(dialect);
                        for (String aSql : sql)
                        {
                            logger.debug("Executing schema statement: " + aSql);
                            session.createSQLQuery(aSql).executeUpdate();
                        }
                        return null;
                    }
                }
        );
    }

}
