package IreulTools.sql.connections;

import IreulTools.collections.ISimpleMap;
import IreulTools.functionalProgramming.IEachPair;
import IreulTools.sql.connections.Exceptions.InitialFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class PostgreSqlConnection implements IConnection {

    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlConnection.class);

    private final Connection cn;
    private final ISimpleMap options;

    public static IConnection create(ISimpleMap options) throws SQLException{
        return new PostgreSqlConnection(options);
    }

    private PostgreSqlConnection(ISimpleMap options)
        throws SQLException{

        int port = 5432;
        try {
            if (!options.get("port").isEmptyOrNull()) {
                port = options.get("port").toInt();
            }
        }
        catch (Exception e){
            throw new InitialFailedException("Option port must be integer");
        }


        if (options.get("host").isEmptyOrNull()) {
            throw new InitialFailedException("Option host required");
        }
        if (options.get("dbname").isEmptyOrNull()) {
            throw new InitialFailedException("Option dbname required");
        }

        if (options.get("user").isEmptyOrNull()) {
            throw new InitialFailedException("Option user required");
        }

        if (options.get("password").isEmptyOrNull()) {
            throw new InitialFailedException("Option password required");
        }


        String url = (new StringBuilder(50))
                .append("jdbc:postgresql://")
                .append(options.get("host").toString())
                .append(":")
                .append(port)
                .append("/")
                .append(options.get("db").toString())
                .toString();

        this.options = options;
        this.cn = DriverManager.getConnection(
                url,
                options.get("user").toString(),
                options.get("password").toString()
        );

    }

    /**
     * Only execute SQL syntax and return data.
     * @param cmd: SQL syntax.
     * @param each: A call back function which renders index & row.
     * @return return false if wanna exit
     * @throws SQLException
     */
    @Override
    public IConnection query(String cmd, IEachPair<Integer,IRow> each) throws SQLException{

        PreparedStatement pst = this.cn.prepareStatement(cmd);
        Row row = new Row(pst.executeQuery());
        int i=0;
        try {
            while (row.next()) {
                if (!each.isContinue(i++,row))
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


    /**
     * Execute SQL syntax and return data with primary key.
     * @param cmd: SQL syntax.
     * @param each: A call back function which renders index & row.
     * @return return false if wanna exit
     * @throws SQLException
     */
    @Override
    public IConnection exec(String cmd, IEachPair<Integer,IRow> each) throws SQLException{

        PreparedStatement pst = this.cn.prepareStatement(cmd,Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        Row row = new Row(rs);
        int i=0;
        try {
            while (row.next()) {
                if (!each.isContinue(i++,row))
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

    @Override
    public String toString() {
        return options.toString();
    }
}
