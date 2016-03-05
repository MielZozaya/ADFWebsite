/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adf.page.SelfHelpSupport.StepOne;

import adf.model.ExerciseAnswers.Exercise2Ans;
import adf.page.AdfSession;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author miel
 */
public class Exercise2Panel extends Panel {

    private Model answer1Model = new Model();
    private Model answer2Model = new Model();
    private Model answer3Model = new Model();

    public Exercise2Panel(String id, boolean editable) {
        super(id);

        List<Exercise2Ans> answersList = ((AdfSession) getSession()).getAdfService().getADFProgramAnswers(Exercise2Ans.class, ((AdfSession) getSession()).getUserName()).getExercise2AnsList();
        if (answersList == null) {
            answersList = new ArrayList<Exercise2Ans>();
        }

        Form form = new Form("form");
        add(form);

        ListView<Exercise2Ans> currentAnswers = new ListView<Exercise2Ans>("currentAnswers", answersList) {

            @Override
            protected void populateItem(ListItem<Exercise2Ans> li) {
                Exercise2Ans ans = li.getModelObject();

                li.add(new MultiLineLabel("question1",ans.getQuestion1()));
                li.add(new MultiLineLabel("question2",ans.getQuestion2()));
                li.add(new MultiLineLabel("question3",ans.getQuestion3()));
            }
        };
        form.add(currentAnswers);
        

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        form.add(feedback);

        Button submitButton = new Button("submitButton") {

            @Override
            public void onSubmit() {
                Exercise2Ans exercise = new Exercise2Ans();
                exercise.setQuestion1((String) answer1Model.getObject());
                exercise.setQuestion2((String) answer2Model.getObject());
                exercise.setQuestion3((String) answer3Model.getObject());

                ((AdfSession) getSession()).getAdfService().addADFProgramAnswer(((AdfSession) getSession()).getUserName(), exercise, Exercise2Ans.class);
                setResponsePage(getPage().getPageClass());

            }
        };
        form.add(submitButton);

        TextArea answer1 = new TextArea("answer1", answer1Model);
        answer1.setRequired(true);
        form.add(answer1);

        TextArea answer2 = new TextArea("answer2", answer2Model);
        answer2.setRequired(true);
        form.add(answer2);

        TextArea answer3 = new TextArea("answer3", answer3Model);
        answer3.setRequired(true);
        form.add(answer3);

        if (!editable) {
            answer1.setVisible(false);
            answer2.setVisible(false);
            answer3.setVisible(false);
            submitButton.setVisible(false);
        }
    }
}
