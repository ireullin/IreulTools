package IreulTools.sql.connections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class Row implements IRow {

    private static final Logger LOG = LoggerFactory.getLogger(Row.class);

    private final ResultSet rs;

    private int size = -1;

    public Row(ResultSet rs){
        this.rs = rs;

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
        try {
            if(this.size==-1) {
                this.size = rs.getMetaData().getColumnCount();
            }
        }
        catch (Exception e){
            LOG.warn("getColumnNames failed",e);
            this.size = -1;
        }
        return this.size;
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columns = new ArrayList<String>(10);
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            // get the column names; column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                columns.add(columnName);
            }
        }
        catch (Exception e){
            LOG.warn("getColumnNames failed",e);
        }
        return columns;
    }
}
