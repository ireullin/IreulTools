package IreulTools.sql.connections;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class Row implements IRow {

    private final ResultSet rs;

    public Row(ResultSet rs){
        this.rs = rs;
    }

    public boolean next() throws SQLException{
        return this.rs.next();
    }

    public void close() throws SQLException {
        this.rs.close();
    }

    @Override
    public ICell index(int i) {
        return new IndexCell(rs, i);
    }

    @Override
    public ResultSet getResultSet() {
        return this.rs;
    }

    @Override
    public ICell column(String name) {
        return new ColumnCell(rs, name);
    }
}
