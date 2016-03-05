/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author miel
 */
public class Category implements Serializable{

    protected String name;
    protected String description;
    protected int version;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Category))
            return false;

        Category category = (Category) o;

        if (!name.equals(category.name))
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Category(");
        sb.append("name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(")");
        return sb.toString();
    }

}
