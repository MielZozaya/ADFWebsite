/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

/**
 *
 * @author miel
 */
public class Exercise5bAns {

    private Long id;
    private int version;

    private String question1;
    private String question2;
    private String question3;

    public Exercise5bAns(String question1, String question2, String question3) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
    }

    public Exercise5bAns() {
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
        if (!(o instanceof Exercise5bAns))
            return false;

        Exercise5bAns exercise5b = (Exercise5bAns) o;

        if (!id.equals(exercise5b.id))
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
        StringBuffer sb = new StringBuffer("Exercise5bAns(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer=");
        sb.append(question1);
        sb.append(", "+question2);
        sb.append(", "+question3);
        sb.append(")");
        return sb.toString();
    }
}
