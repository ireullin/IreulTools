package IreulTools.sql.connections;

import IreulTools.collections.ISimpleMap;
import IreulTools.functionalProgramming.IEachPair;
import IreulTools.functionalProgramming.IEachPairUntilEnd;
import IreulTools.sql.connections.Exceptions.InitialFailedException;
import IreulTools.sql.statements.ISQLSyntax;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class PostgreSqlConnection implements IConnection {

    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlConnection.class);

    private final Connection cn;
    private final ISimpleMap options;

    public static IConnection create(ISimpleMap options) throws Exception{
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
                .append(options.get("dbname").toString())
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
    public IConnection query(String cmd, IEachPair<Boolean,Integer,IRow> each) throws Exception{

        PreparedStatement pst = this.cn.prepareStatement(cmd);
        Row row = new Row(pst.executeQuery());
        int i=0;
        try {
            while (row.next()) {
                if (!each.put(i++,row))
                    break;
            }
        }
        catch (Exception e){
            throw e;
        }
        finally {
            row.close();
            pst.close();
        }

        return this;
    }

    @Override
    public List<Map<String,String>> queryToList(String cmd) throws Exception {
        List<Map<String,String>> buffs = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        query(cmd, (i,row) -> {
            if(i==0){
                columns.addAll(row.getColumnNames());
            }
            Map<String,String> buff = new TreeMap<>();
            for(String column : columns){
                buff.put(column, row.column(column).toString());
            }
            buffs.add(buff);
            return true;
        });
        return buffs;
    }

    /**
     * Execute SQL syntax and return data with primary key.
     * @param cmd: SQL syntax.
     * @param each: A call back function which renders index & row.
     * @return return false if wanna exit
     * @throws SQLException
     */
    @Override
    public IConnection exec(String cmd, IEachPair<Boolean,Integer,IRow> each) throws Exception{

        PreparedStatement pst = this.cn.prepareStatement(cmd,Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        Row row = new Row(rs);
        int i=0;
        try {
            while (row.next()) {
                if (!each.put(i++,row))
                    break;
            }
        }
        catch (Exception e){
            throw e;
        }
        finally {
            row.close();
            pst.close();
        }

        return this;
    }

    @Override
    public List<Map<String,String>> execToList(String cmd) throws Exception{
        List<Map<String,String>> buffs = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        exec(cmd, (i,row) -> {
            if(i==0){
                columns.addAll(row.getColumnNames());
            }
            Map<String,String> buff = new TreeMap<>();
            for(String column : columns){
                buff.put(column, row.column(column).toString());
            }
            buffs.add(buff);
            return true;
        });
        return buffs;
    }

    /**
     * Execute mutiple SQL syntax, splited with ';' , and not return anything.
     * @param cmd: SQL syntax.
     * @return return no return
     * @throws SQLException
     */
    @Override
    public IConnection execMutiCmd(String cmd) throws Exception{

        PreparedStatement pst = this.cn.prepareStatement(cmd);
        int rc = pst.executeUpdate();
        pst.close();

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
