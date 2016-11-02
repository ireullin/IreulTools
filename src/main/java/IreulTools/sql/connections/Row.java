package IreulTools.sql.connections;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class Row implements IRow {

    private final ResultSet rs;
    private int size;

    public Row(ResultSet rs){
        this.rs = rs;
        try {
            this.size = rs.getMetaData().getColumnCount();
        }
        catch (Exception e){
            this.size = -1;
        }
    }

    public boolean next() throws SQLException{
        return this.rs.next();
    }

    public void close() throws SQLException {
        this.rs.close();
    }

    public ResultSet getResultSet() {
        return this.rs;
    }

    @Override
    public ICell index(int i) {
        return new IndexCell(rs, i);
    }

    @Override
    public ICell column(String name) {
        return new ColumnCell(rs, name);
    }

    @Override
    public int size() {
        return this.size;
    }
}
