package IreulTools.sql.connections;

import IreulTools.functionalProgramming.IEachPair;
import IreulTools.sql.statements.ISQLSyntax;

import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IConnection extends AutoCloseable{
    public IConnection query(String cmd, IEachPair<Integer,IRow> each) throws SQLException;
    public IConnection query(ISQLSyntax sql, IEachPair<Integer,IRow> each) throws SQLException;
    public IConnection exec(String cmd, IEachPair<Integer,IRow> each) throws SQLException;
    public IConnection exec(ISQLSyntax sql, IEachPair<Integer,IRow> each) throws SQLException;
    public IConnection execMutiCmd(String cmd) throws SQLException;
}
