package adf.dao.hibernate;

import adf.dao.ADFProgramAnswersDAO;
import adf.model.ADFProgramAnswers;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ADFProgramAnswersDAOImpl extends HibernateDaoSupport implements ADFProgramAnswersDAO {

    public List<ADFProgramAnswers> getADFProgramAnswers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void save(ADFProgramAnswers adfProgramAnswers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(ADFProgramAnswers adfProgramAnswers) {
        getHibernateTemplate().update(adfProgramAnswers);
    }



}
