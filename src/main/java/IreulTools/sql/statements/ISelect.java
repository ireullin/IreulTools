package IreulTools.sql.statements;

import java.util.List;

/**
 * Created by ireullin on 2016/11/1.
 */
public interface ISelect {
    public ISelect where(String field, Object val);
    public ISelect where(String syntax);

    public ISelect whereIn(String field, List<? extends Object> val);
    public ISelect whereIn(String field, Object[] val);


//    public ISelect whereIn(String field, List<? extends Integer> vals);
//    public ISelect whereIn(String field, List<Float> vals);

}
