package adf.dao;

import adf.model.ViewHistory;
import java.util.List;

/**
 *
 * @author miel
 */
public interface ViewHistoryDAO {

    public void save(ViewHistory viewHistory);
    public List<ViewHistory> getViewHistories();
}
