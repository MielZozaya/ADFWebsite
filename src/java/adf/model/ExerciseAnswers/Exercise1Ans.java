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
public class Exercise1Ans implements Serializable{

    private Long id;
    private int version;

    private boolean option1;
    private boolean option2;
    private boolean option3;
    private boolean option4;
    private boolean option5;
    private boolean option6;
    private boolean option7;
    private boolean option8;
    private boolean option9;

    public Exercise1Ans(boolean option1, boolean option2, boolean option3, boolean option4, boolean option5, boolean option6, boolean option7, boolean option8, boolean option9) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.option7 = option7;
        this.option8 = option8;
        this.option9 = option9;
    }

    public Exercise1Ans() {
        this.option1 = false;
        this.option2 = false;
        this.option3 = false;
        this.option4 = false;
        this.option5 = false;
        this.option6 = false;
        this.option7 = false;
        this.option8 = false;
        this.option9 = false;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public boolean isOption1() {
        return option1;
    }

    public void setOption1(boolean option1) {
        this.option1 = option1;
    }

    public boolean isOption2() {
        return option2;
    }

    public void setOption2(boolean option2) {
        this.option2 = option2;
    }

    public boolean isOption3() {
        return option3;
    }

    public void setOption3(boolean option3) {
        this.option3 = option3;
    }

    public boolean isOption4() {
        return option4;
    }

    public void setOption4(boolean option4) {
        this.option4 = option4;
    }

    public boolean isOption5() {
        return option5;
    }

    public void setOption5(boolean option5) {
        this.option5 = option5;
    }

    public boolean isOption6() {
        return option6;
    }

    public void setOption6(boolean option6) {
        this.option6 = option6;
    }

    public boolean isOption7() {
        return option7;
    }

    public void setOption7(boolean option7) {
        this.option7 = option7;
    }

    public boolean isOption8() {
        return option8;
    }

    public void setOption8(boolean option8) {
        this.option8 = option8;
    }

    public boolean isOption9() {
        return option9;
    }

    public void setOption9(boolean option9) {
        this.option9 = option9;
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
        if (!(o instanceof Exercise1Ans))
            return false;

        Exercise1Ans exercise1 = (Exercise1Ans) o;

        if ((option1 ^ exercise1.option1))
            return false;
        if ((option2 ^ exercise1.option2))
            return false;
        if ((option3 ^ exercise1.option3))
            return false;
        if ((option4 ^ exercise1.option4))
            return false;
        if ((option5 ^ exercise1.option5))
            return false;
        if ((option6 ^ exercise1.option6))
            return false;
        if ((option7 ^ exercise1.option7))
            return false;
        if ((option8 ^ exercise1.option8))
            return false;
        if ((option9 ^ exercise1.option9))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        if (id == null) {
            return 127;
        }
        return id.hashCode();
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("Exercise1Ans(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer=");
        sb.append(option1);
        sb.append(", "+option2);
        sb.append(", "+option3);
        sb.append(", "+option4);
        sb.append(", "+option5);
        sb.append(", "+option6);
        sb.append(", "+option7);
        sb.append(", "+option8);
        sb.append(", "+option9);
        sb.append(")");
        return sb.toString();
    }

}
