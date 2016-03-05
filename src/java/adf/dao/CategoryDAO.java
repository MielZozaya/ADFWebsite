package adf.dao;

import adf.model.Category;
import java.util.List;

/**
 * 
 * @author miel
 */
public interface CategoryDAO
{
    public Category loadCategoryByName(String name);

    public List<Category> getCategory();

    public void save(Category category);

    public void delete(Category category);
}