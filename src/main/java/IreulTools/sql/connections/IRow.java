package IreulTools.sql.connections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IRow {
    public List<String> getColumnNames();
    public ICell column(String name);
    public ICell index(int i);
    public int size();
}
