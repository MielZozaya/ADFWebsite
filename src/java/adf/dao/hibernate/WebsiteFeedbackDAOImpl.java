package adf.dao.hibernate;

import adf.dao.WebsiteFeedbackDAO;
import adf.model.WebsiteFeedback;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author miel
 */
public class WebsiteFeedbackDAOImpl extends HibernateDaoSupport implements WebsiteFeedbackDAO {

    public List<WebsiteFeedback> getWebsiteFeedbackList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void save(WebsiteFeedback websiteFeedback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(WebsiteFeedback websiteFeedback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
