/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport.SelfHelp;

import adf.model.ExerciseAnswers.Exercise1Ans;
import adf.page.AdfSession;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
@AuthorizeInstantiation({"ROLE_client","ROLE_professional"})
public class Exercise1Panel extends Panel {

    private Model option1Model = new Model();
    private Model option2Model = new Model();
    private Model option3Model = new Model();
    private Model option4Model = new Model();
    private Model option5Model = new Model();
    private Model option6Model = new Model();
    private Model option7Model = new Model();
    private Model option8Model = new Model();
    private Model option9Model = new Model();

    public Exercise1Panel(String id, boolean editable) {
        super(id);

        /* The following code writes the code that gives the user the following check options
         *  I am living with someone who is misusing alcohol or drugs
         *  I do not know how to cope with the situation
         *  The situation is making me physically ill
         *  I feel emotionally abused
         *  I feel very alone and unhappy
         *  I worry aI feel very alone and unhappybout the effect on my children
         *  I worry about the effects on others in my family
         *  I'm looking for something to help me deal with the situation
         *  I feel ashamed and confused and don't know which way to turn
         */

        Exercise1Ans answers = ((AdfSession)getSession()).getAdfService().getADFProgramAnswers(Exercise1Ans.class, ((AdfSession)getSession()).getUserName()).getExercise1Ans();
        if(answers == null){
            answers = new Exercise1Ans();
        }
        
        Form form = new Form("form");
        add(form);
        
        
        Button submitButton = new Button("submit"){

            @Override
            public void onSubmit() {
                Exercise1Ans exercise = new Exercise1Ans();
                exercise.setOption1(((Boolean)option1Model.getObject()).booleanValue());
                exercise.setOption2(((Boolean)option2Model.getObject()).booleanValue());
                exercise.setOption3(((Boolean)option3Model.getObject()).booleanValue());
                exercise.setOption4(((Boolean)option4Model.getObject()).booleanValue());
                exercise.setOption5(((Boolean)option5Model.getObject()).booleanValue());
                exercise.setOption6(((Boolean)option6Model.getObject()).booleanValue());
                exercise.setOption7(((Boolean)option7Model.getObject()).booleanValue());
                exercise.setOption8(((Boolean)option8Model.getObject()).booleanValue());
                exercise.setOption9(((Boolean)option9Model.getObject()).booleanValue());
                
                ((AdfSession)getSession()).getAdfService().addADFProgramAnswer(((AdfSession)getSession()).getUserName(), exercise, Exercise1Ans.class);
                setResponsePage(getPage().getPageClass());

            }

        };
        form.add(submitButton);

        CheckBox option1 = new CheckBox("option1", option1Model);
        option1Model.setObject(new Boolean(answers.isOption1()));
        form.add(option1);

        CheckBox option2 = new CheckBox("option2", option2Model);
        option2Model.setObject(new Boolean(answers.isOption2()));
        form.add(option2);
        
        CheckBox option3 = new CheckBox("option3", option3Model);
        option3Model.setObject(new Boolean(answers.isOption3()));
        form.add(option3);
        
        CheckBox option4 = new CheckBox("option4", option4Model);
        option4Model.setObject(new Boolean(answers.isOption4()));
        form.add(option4);
        
        CheckBox option5 = new CheckBox("option5", option5Model);
        option5Model.setObject(new Boolean(answers.isOption5()));
        form.add(option5);
        
        CheckBox option6 = new CheckBox("option6", option6Model);
        option6Model.setObject(new Boolean(answers.isOption6()));
        form.add(option6);
        
        CheckBox option7 = new CheckBox("option7", option7Model);
        option7Model.setObject(new Boolean(answers.isOption7()));
        form.add(option7);
        
        CheckBox option8 = new CheckBox("option8", option8Model);
        option8Model.setObject(new Boolean(answers.isOption8()));
        form.add(option8);
        
        CheckBox option9 = new CheckBox("option9", option9Model);
        option9Model.setObject(new Boolean(answers.isOption9()));
        form.add(option9);
        
        /* Set as editable form or not*/
        if(!editable){
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
            option5.setEnabled(false);
            option6.setEnabled(false);
            option7.setEnabled(false);
            option8.setEnabled(false);
            option9.setEnabled(false);
            submitButton.setVisible(false);
        }
    }
}
