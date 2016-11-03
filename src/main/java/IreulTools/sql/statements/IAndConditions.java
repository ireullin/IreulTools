package IreulTools.sql.statements;

import java.util.List;

/**
 * Created by tech0039 on 2016/11/2.
 */
public interface IAndConditions {
    public IAndConditions put(String field, Object val);
    public IAndConditions put(String syntax);
    public String toString(boolean doesIndent);
}
