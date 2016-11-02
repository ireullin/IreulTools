package IreulTools.sql.statements;

import java.util.List;

/**
 * Created by ireullin on 2016/11/1.
 */
public interface ISelect {
    public ISelect where(String field, Object val);
    public ISelect where(String syntax);
//    public ISelect where(ISelect subquery);
    public ISelect columns(String syntax);
    public ISelect columns(String[] syntax);
    public ISelect columns(List<String> syntax);
    public ISelect count();
    public ISelect distinct(String column);
    public ISelect orderByDesc(String column);
    public ISelect orderByAsc(String column);
    public ISelect groupWithCount(String syntax);
    public ISelect groupWithCount(String[] syntax);
    public ISelect groupWithCount(List<String> syntax);
//    public ISelect groupWithCount(String syntax, String having);
//    public ISelect groupWithCount(String[] syntax, String having);
//    public ISelect groupWithCount(List<String> syntax, String having);

}
