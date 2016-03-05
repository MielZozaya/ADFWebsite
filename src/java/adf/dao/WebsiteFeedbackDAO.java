package adf.dao;


import adf.model.WebsiteFeedback;
import java.util.List;

/**
 *
 * @author miel
 */
public interface WebsiteFeedbackDAO
{
    public List<WebsiteFeedback> getWebsiteFeedbackList();

    public void save(WebsiteFeedback websiteFeedback);

    public void update(WebsiteFeedback websiteFeedback);
}
