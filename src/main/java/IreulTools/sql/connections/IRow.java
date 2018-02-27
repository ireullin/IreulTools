package IreulTools.sql.connections;

import java.util.List;
import java.util.Map;

/**
 * Created by tech0039 on 2016/11/1.
 */
public interface IRow {
    public List<String> getColumnNames();
    public ICell column(String name);
    public ICell index(int i);
    public int size();
    public Map<String,String> toMap();
    public List<String> toList();
}
