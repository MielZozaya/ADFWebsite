/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

import java.io.Serializable;

/**
 *
 * @author miel
 */
public class Exercise2Ans implements Serializable{

    private Long id;
    private int version;

    private String question1;
    private String question2;
    private String question3;

    public Exercise2Ans(String question1, String question2, String question3) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
    }

    public Exercise2Ans() {
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
        if (!(o instanceof Exercise2Ans))
            return false;

        Exercise2Ans exercise2 = (Exercise2Ans) o;

        if (!id.equals(exercise2.id))
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
        StringBuffer sb = new StringBuffer("Exercise2Ans(");
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
