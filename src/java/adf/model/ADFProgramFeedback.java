/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import java.io.Serializable;


/**
 *
 * @author miel
 */
public class ADFProgramFeedback implements Serializable {

    private boolean ans1_1;// TRUE tick, FALSE not tick
    private boolean ans1_2;
    private boolean ans1_3;
    private boolean ans1_4;
    private boolean ans1_5;

    private boolean ans2_1;// TRUE tick, FALSE not tick
    private boolean ans2_2;
    private boolean ans2_3;
    private boolean ans2_4;
    private boolean ans2_5;
    private boolean ans2_6;
    private boolean ans2_7;

    private short ans3; // 0 yes, 1 no, 2 not sure

    private boolean ans4; // TRUE yes, FALSE no

    private String ans5;

    private String ans6;

    private Long id;
    private int version;
    private AdfUser user;

    public ADFProgramFeedback() {
    }

    public ADFProgramFeedback(AdfUser user, boolean ans1_1, boolean ans1_2, boolean ans1_3, boolean ans1_4, boolean ans1_5, boolean ans2_1, boolean ans2_2, boolean ans2_3, boolean ans2_4, boolean ans2_5, boolean ans2_6, boolean ans2_7, short ans3, boolean ans4, String ans5, String ans6) {
        this.user = user;
        this.ans1_1 = ans1_1;
        this.ans1_2 = ans1_2;
        this.ans1_3 = ans1_3;
        this.ans1_4 = ans1_4;
        this.ans1_5 = ans1_5;
        this.ans2_1 = ans2_1;
        this.ans2_2 = ans2_2;
        this.ans2_3 = ans2_3;
        this.ans2_4 = ans2_4;
        this.ans2_5 = ans2_5;
        this.ans2_6 = ans2_6;
        this.ans2_7 = ans2_7;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.ans6 = ans6;
    }

    public boolean isAns1_1() {
        return ans1_1;
    }

    public void setAns1_1(boolean ans1_1) {
        this.ans1_1 = ans1_1;
    }

    public boolean isAns1_2() {
        return ans1_2;
    }

    public void setAns1_2(boolean ans1_2) {
        this.ans1_2 = ans1_2;
    }

    public boolean isAns1_3() {
        return ans1_3;
    }

    public void setAns1_3(boolean ans1_3) {
        this.ans1_3 = ans1_3;
    }

    public boolean isAns1_4() {
        return ans1_4;
    }

    public void setAns1_4(boolean ans1_4) {
        this.ans1_4 = ans1_4;
    }

    public boolean isAns1_5() {
        return ans1_5;
    }

    public void setAns1_5(boolean ans1_5) {
        this.ans1_5 = ans1_5;
    }

    public boolean isAns2_1() {
        return ans2_1;
    }

    public void setAns2_1(boolean ans2_1) {
        this.ans2_1 = ans2_1;
    }

    public boolean isAns2_2() {
        return ans2_2;
    }

    public void setAns2_2(boolean ans2_2) {
        this.ans2_2 = ans2_2;
    }

    public boolean isAns2_3() {
        return ans2_3;
    }

    public void setAns2_3(boolean ans2_3) {
        this.ans2_3 = ans2_3;
    }

    public boolean isAns2_4() {
        return ans2_4;
    }

    public void setAns2_4(boolean ans2_4) {
        this.ans2_4 = ans2_4;
    }

    public boolean isAns2_5() {
        return ans2_5;
    }

    public void setAns2_5(boolean ans2_5) {
        this.ans2_5 = ans2_5;
    }

    public boolean isAns2_6() {
        return ans2_6;
    }

    public void setAns2_6(boolean ans2_6) {
        this.ans2_6 = ans2_6;
    }

    public boolean isAns2_7() {
        return ans2_7;
    }

    public void setAns2_7(boolean ans2_7) {
        this.ans2_7 = ans2_7;
    }

    public short getAns3() {
        return ans3;
    }

    public void setAns3(short ans3) {
        this.ans3 = ans3;
    }

    public boolean isAns4() {
        return ans4;
    }

    public void setAns4(boolean ans4) {
        this.ans4 = ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public void setAns5(String ans5) {
        this.ans5 = ans5;
    }

    public String getAns6() {
        return ans6;
    }

    public void setAns6(String ans6) {
        this.ans6 = ans6;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public AdfUser getUser() {
        return user;
    }

    public void setUser(AdfUser user) {
        this.user = user;
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
        if (!(o instanceof ADFProgramFeedback))
            return false;

        ADFProgramFeedback feedback = (ADFProgramFeedback) o;

        if (!id.equals(feedback.id))
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
        StringBuffer sb = new StringBuffer("ADFProgramFeedback(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Answer1=");
        sb.append(ans1_1);
        sb.append(", "+ans1_2);
        sb.append(", "+ans1_3);
        sb.append(", "+ans1_4);
        sb.append(", "+ans1_5);
        sb.append(", Answer2=");
        sb.append(ans2_1);
        sb.append(", "+ans2_2);
        sb.append(", "+ans2_3);
        sb.append(", "+ans2_4);
        sb.append(", "+ans2_5);
        sb.append(", "+ans2_6);
        sb.append(", "+ans2_7);
        sb.append(", Answer3=");
        sb.append(ans3+"-0 yes, 1 no, 2 not sure-");
        sb.append(", Answer4=");
        sb.append(ans4);
        sb.append(", Answer5=");
        sb.append(ans5);
        sb.append(", Answer6=");
        sb.append(ans6);
        sb.append(")");
        return sb.toString();
    }
    
    
}
