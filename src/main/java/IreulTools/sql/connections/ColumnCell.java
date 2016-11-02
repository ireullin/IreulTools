package IreulTools.sql.connections;

import IreulTools.datetime.Datetime;
import IreulTools.datetime.IDatetime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by tech0039 on 2016/11/1.
 */
public class ColumnCell implements ICell {

    private static final Logger LOG = LoggerFactory.getLogger(ColumnCell.class);

    private final String column;
    private final ResultSet rs;

    public ColumnCell(ResultSet rs, String column){
        this.rs = rs;
        this.column = column;
    }

    @Override
    public String toString(String defaultVal) {
        try{
            return rs.getString(column);
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

    @Override
    public String toString() {
        return toString(null);
    }

    @Override
    public float toFloat() throws SQLException {
        return rs.getFloat(column);
    }

    @Override
    public float toFloat(float defaultVal) {
        try{
            return toFloat();
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

    @Override
    public long toLong() throws SQLException {
        return rs.getLong(column);
    }

    @Override
    public long toLong(long defaultVal) {
        try{
            return toLong();
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

    @Override
    public double toDouble() throws SQLException {
        return rs.getFloat(column);
    }

    @Override
    public double toDouble(double defaultVal) {
        try{
            return toDouble();
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

    @Override
    public int toInt() throws SQLException{
        return rs.getInt(column);
    }

    @Override
    public int toInt(int defaultVal) {
        try{
            return toInt();
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

    @Override
    public IDatetime toDatetime() throws SQLException{
        return new Datetime( rs.getTimestamp(column) );
    }

    @Override
    public IDatetime toDatetime(IDatetime defaultVal) {
        try{
            return toDatetime();
        }
        catch (SQLException e){
            return defaultVal;
        }
    }

}
