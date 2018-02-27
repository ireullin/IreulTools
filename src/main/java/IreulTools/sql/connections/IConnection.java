package IreulTools.sql.connections;

import IreulTools.functionalProgramming.IEachPair;
import IreulTools.functionalProgramming.IEachPairUntilEnd;
import IreulTools.sql.statements.ISQLSyntax;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IConnection extends AutoCloseable{
    public IConnection query(String cmd, IEachPair<Boolean,Integer,IRow> each) throws Exception;
    public IConnection exec(String cmd, IEachPair<Boolean,Integer,IRow> each) throws Exception;
    public IConnection execMutiCmd(String cmd) throws Exception;
    public List<Map<String,String>> queryToList(String cmd) throws Exception;
    public List<Map<String,String>> execToList(String cmd) throws Exception;
}
