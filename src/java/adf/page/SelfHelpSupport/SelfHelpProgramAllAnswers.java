/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.page.SelfHelpSupport;

import adf.model.ADFProgramAnswers;
import adf.page.AdfWebPage;
import adf.page.ErrorPage;
import adf.page.SelfHelpSupport.SelfHelp.Exercise1Panel;
import adf.page.SelfHelpSupport.StepOne.Exercise2Panel;
import adf.page.SelfHelpSupport.StepOne.Exercise3Panel;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_professional","ROLE_client"})
public class SelfHelpProgramAllAnswers extends AdfWebPage{

    public SelfHelpProgramAllAnswers(String username) {

        ADFProgramAnswers adfProgramAnswers = getAdfService().getAllADFProgramAnswers(username, getAdfSession().getUserName());

        if(adfProgramAnswers == null){
            setResponsePage(new ErrorPage("You have no permission to the information you requested", this));
            adfProgramAnswers = new ADFProgramAnswers();
        }

        if(adfProgramAnswers.getExercise1Ans() !=null) add(new Exercise1Panel("exercise1", false));
        else add(new Label("exercise1", "Exercise not done yet."));

        if(!adfProgramAnswers.getExercise2AnsList().isEmpty()) add(new Exercise2Panel("exercise2", false));
        else add(new Label("exercise2", "Exercise not done yet."));

        if(!adfProgramAnswers.getExercise3AnsList().isEmpty()) add(new Exercise3Panel("exercise3", false));
        else add(new Label("exercise3", "Exercise not done yet."));
    }

}
