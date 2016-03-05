/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adf.dao;

import adf.model.Tool;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ToolDAO {

    public List<Tool> getTools();

    public void save(Tool tool);

    public Tool getTool(Long id);

    public void delete(Tool tool);

}
