package adf.dao;


import adf.model.ADFProgramFeedback;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ADFProgramFeedbackDAO
{
    public List<ADFProgramFeedback> getADFProgramFeedback();

    public void save(ADFProgramFeedback adfProgramFeedback);

    public void update(ADFProgramFeedback adfProgramFeedback);
}
