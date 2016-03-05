package adf.model;

import java.io.Serializable;
import org.acegisecurity.GrantedAuthority;

/**
 *
 * @author miel
 */
public class Role implements Serializable, GrantedAuthority
{
    private String name;
    private String description;
    private int version;

    protected Role()
    {
    }

    public Role(String name, String desc)
    {
        this.name = name;
        this.description = desc;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAuthority()
    {
        return getName();
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Role))
            return false;

        final Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return (name != null ? name.hashCode() : 0);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Role(");
        sb.append("name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(")");
        return sb.toString();
    }
}