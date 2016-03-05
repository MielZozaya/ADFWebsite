/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

/**
 *
 * @author miel
 */
public class Exercise4Ans {

    private Long id;
    private int version;

    private String question1;

    public Exercise4Ans(String question1) {
        this.question1 = question1;
    }

    public Exercise4Ans() {
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
        if (!(o instanceof Exercise4Ans))
            return false;

        Exercise4Ans exercise4 = (Exercise4Ans) o;

        if (!id.equals(exercise4.id))
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
        StringBuffer sb = new StringBuffer("Exercise4Ans(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer=");
        sb.append(question1);
        sb.append(")");
        return sb.toString();
    }
}
