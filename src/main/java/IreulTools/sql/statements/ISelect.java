package IreulTools.sql.statements;

import IreulTools.functionalProgramming.ITap;

import java.util.List;

/**
 * Created by ireullin on 2016/11/1.
 */
public interface ISelect extends ISQLSyntax{
    public ISelect where(String field, Object val);
    public ISelect where(String syntax);
    public ISelect where(String field, ISelect subquery);
    public ISelect columns(String syntax);
    public ISelect columns(String[] syntax);
    public ISelect columns(List<String> syntax);
    public ISelect count();
    public ISelect distinct(String column);
    public ISelect orderDescBy(String column);
    public ISelect orderAscBy(String column);
    public ISelect groupWithCount(String syntax);
    public ISelect groupWithCount(String[] syntax);
    public ISelect groupWithCount(List<String> syntax);
    public ISelect groupWithCount(String syntax, String having);
    public ISelect groupWithCount(String[] syntax, String having);
    public ISelect groupWithCount(List<String> syntax, String having);
    public ISelect limit(int i);
    public ISelect offset(int i);
    public ISelect tap(ITap<String> debugMsg);
}
