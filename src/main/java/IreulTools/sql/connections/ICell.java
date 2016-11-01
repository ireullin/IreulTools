package IreulTools.sql.connections;

import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface ICell {
    public String toString();
    public String toString(String defaultVal);

    public int toInt() throws SQLException;
    public int toInt(int defaultVal);

    public float toFloat() throws SQLException;
    public float toFloat(float defaultVal);

    public long toLong() throws SQLException;
    public long toLong(long defaultVal);

    public double toDouble() throws SQLException;
    public double toDouble(double defaultVal);
}
