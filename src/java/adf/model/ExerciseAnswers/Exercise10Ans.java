/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model.ExerciseAnswers;

/**
 *
 * @author miel
 */
public class Exercise10Ans {

    private Long id;
    private int version;

    private String supportive;
    private String non_suportive;

    public Exercise10Ans(String supportive, String non_suportive) {
        this.supportive = supportive;
        this.non_suportive = non_suportive;
    }

    public Exercise10Ans() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNon_suportive() {
        return non_suportive;
    }

    public void setNon_suportive(String non_suportive) {
        this.non_suportive = non_suportive;
    }

    public String getSupportive() {
        return supportive;
    }

    public void setSupportive(String supportive) {
        this.supportive = supportive;
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
        if (!(o instanceof Exercise10Ans))
            return false;

        Exercise10Ans exercise10 = (Exercise10Ans) o;

        if (!id.equals(exercise10.id))
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
        StringBuffer sb = new StringBuffer("Exercise10Ans(");
        sb.append("id=");
        sb.append(id);
        sb.append(", Supportive=");
        sb.append(supportive);
        sb.append(", Non-supportive=");
        sb.append(non_suportive);
        sb.append(")");
        return sb.toString();
    }
}
