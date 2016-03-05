/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

/**
 *
 * @author miel
 */
public class Exercise7Ans {

    private Long id;
    private int version;

    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;

    public Exercise7Ans(String question1, String question2, String question3, String question4, String question5) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    public Exercise7Ans() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Exercise7Ans))
            return false;

        Exercise7Ans exercise7 = (Exercise7Ans) o;

        if (!id.equals(exercise7.id))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("Exercise7Ans(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer=");
        sb.append(question1);
        sb.append(", "+question2);
        sb.append(", "+question3);
        sb.append(", "+question4);
        sb.append(", "+question5);
        sb.append(")");
        return sb.toString();
    }
}
