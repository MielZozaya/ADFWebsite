package adf.model;

import java.io.Serializable;

/**
 *
 * @author miel
 */
public class ClientCategory extends Category implements Serializable{

    public ClientCategory(){
        super();
    }

    public ClientCategory(String name, String description){
        super(name, description);
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof ClientCategory))
            return false;

        ClientCategory category = (ClientCategory) o;

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
        StringBuilder sb = new StringBuilder("ClientCategory(");
        sb.append("name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(")");
        return sb.toString();
    }
}
