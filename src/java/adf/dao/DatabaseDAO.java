package adf.dao;


import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.context.ApplicationContextAware;


/**
 * User: aps
 * Date: 16-Feb-2008
 * Time: 04:21:58
 */
public interface DatabaseDAO
{
    LocalSessionFactoryBean getLsfb();

    void setLsfb(LocalSessionFactoryBean lsfb);

    void createDB();

    void destroyDB();
}
