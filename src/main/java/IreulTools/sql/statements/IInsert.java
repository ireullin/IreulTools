package IreulTools.sql.statements;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IInsert extends ISQLSyntax{
    public IInsert put(Object column, Object value);
}
