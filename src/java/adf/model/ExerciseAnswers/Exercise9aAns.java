/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

/**
 *
 * @author miel
 */
public class Exercise9aAns {

    private Long id;
    private int version;

    private String question1;
    private String question2;

    public Exercise9aAns(String question1, String question2, String question3) {
        this.question1 = question1;
        this.question2 = question2;
    }

    public Exercise9aAns() {
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
        if (!(o instanceof Exercise9aAns))
            return false;

        Exercise9aAns exercise9a = (Exercise9aAns) o;

        if (!id.equals(exercise9a.id))
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
        StringBuffer sb = new StringBuffer("Exercise9aAns(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer=");
        sb.append(question1);
        sb.append(", "+question2);
        sb.append(")");
        return sb.toString();
    }
}
