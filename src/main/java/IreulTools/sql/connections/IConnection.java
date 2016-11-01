package IreulTools.sql.connections;

import IreulTools.functionalProgramming.IEachPair;

import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IConnection extends AutoCloseable{
    public IConnection query(String cmd, IEachPair<IRow,Integer> each) throws SQLException;
    public IConnection exec(String cmd, IEachPair<IRow,Integer> each) throws SQLException;
}
