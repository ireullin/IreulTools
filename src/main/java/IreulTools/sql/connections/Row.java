package IreulTools.sql.connections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class Row implements IRow, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(Row.class);

    private final ResultSet rs;

    private int size = -1;

    public Row(ResultSet rs){
        this.rs = rs;

    }

    public boolean next() throws SQLException{
        return this.rs.next();
    }

    @Override
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

    @Override
    public Map<String, String> toMap() {
        Map<String,String> rc = new TreeMap<>();
        for(String colName : getColumnNames()){
            rc.put(colName, column(colName).toString() );
        }
        return rc;
    }

    @Override
    public List<String> toList() {
        List<String> rc = new ArrayList<>(10);
        for(int i=0; i<size(); i++){
            rc.add(index(i).toString());
        }
        return rc;
    }
}
