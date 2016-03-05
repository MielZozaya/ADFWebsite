package adf.dao.hibernate;

import adf.dao.ADFProgramFeedbackDAO;
import adf.model.ADFProgramFeedback;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class ADFProgramFeedbackDAOImpl extends HibernateDaoSupport implements ADFProgramFeedbackDAO {

    public List<ADFProgramFeedback> getADFProgramFeedback() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void save(ADFProgramFeedback adfProgramFeedback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(ADFProgramFeedback adfProgramFeedback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
