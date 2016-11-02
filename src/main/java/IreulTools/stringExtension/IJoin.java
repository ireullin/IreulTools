package IreulTools.stringExtension;

import java.util.List;

/**
 * Created by ireullin on 2016/10/23.
 */
public interface IJoin {
    public IJoin and(Object o);
    public IJoin and(Object[] arr);
    public IJoin with(String symbol);
}
