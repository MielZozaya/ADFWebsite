package adf.dao;


import adf.model.ADFProgramAnswers;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ADFProgramAnswersDAO
{
    public List<ADFProgramAnswers> getADFProgramAnswers();

    public void save(ADFProgramAnswers adfProgramAnswers);

    public void update(ADFProgramAnswers adfProgramAnswers);
}
