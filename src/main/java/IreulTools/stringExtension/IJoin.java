package IreulTools.stringExtension;

import java.util.List;

/**
 * Created by ireullin on 2016/10/23.
 */
public interface IJoin {
    public IJoin and(String s);
    public IJoin and(Object p);
    public IJoin and(String[] arr);
    public IJoin and(List<String> list);
    public IJoin with(String symbol);
}
