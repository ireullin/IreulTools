package IreulTools.sql.connections;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IRow {

    public ResultSet getResultSet();
    public ICell column(String name);
    public ICell index(int i);
}
