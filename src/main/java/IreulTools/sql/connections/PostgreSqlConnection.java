package IreulTools.sql.connections;

import IreulTools.functionalProgramming.IEachPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class PostgreSqlConnection implements IConnection {

    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlConnection.class);

    private final Connection cn;

    public static IConnection create(String host, String initialDB, String user, String password)
            throws SQLException{
        return new PostgreSqlConnection(host, initialDB, user, password, 5432);
    }

    public static IConnection create(String host, String initialDB, String user, String password, int port)
            throws SQLException{
        return new PostgreSqlConnection(host, initialDB, user, password, port);
    }

    private PostgreSqlConnection(String host, String initialDB, String user, String password, int port)
        throws SQLException{

        String url = (new StringBuilder(50))
                .append("jdbc:postgresql://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(initialDB)
                .toString();

        this.cn =DriverManager.getConnection(url, user, password);
    }


    public IConnection query(String cmd, IEachPair<IRow,Integer> each) throws SQLException{

        PreparedStatement pst = this.cn.prepareStatement(cmd);
        Row row = new Row(pst.executeQuery());
        int i=0;
        try {
            while (row.next()) {
                if (!each.isContinue(row, i++))
                    break;
            }
        }
        catch (SQLException e){
            throw e;
        }
        finally {
            row.close();
            pst.close();
        }

        return this;
    }


    public IConnection exec(String cmd, IEachPair<IRow,Integer> each) throws SQLException{

        PreparedStatement pst = this.cn.prepareStatement(cmd,Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        Row row = new Row(rs);
        int i=0;
        try {
            while (row.next()) {
                if (!each.isContinue(row, i++))
                    break;
            }
        }
        catch (SQLException e){
            throw e;
        }
        finally {
            row.close();
            pst.close();
        }

        return this;
    }


    @Override
    public void close() throws Exception {
        this.cn.close();
    }

}
