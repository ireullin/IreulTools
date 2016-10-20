package IreulTools.Collections;

import java.util.Map;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface ISimpleMap extends Map<String,IWrapper> {
    public ISimpleMap zip(Object[] keys, Object[] vals);
    public ISimpleMap zip(Object[] keys, Object[] vals, Object defaultVal);
    public ISimpleMap mergeWithOld(Map<? extends String, ? extends IWrapper> m);
    public ISimpleMap mergeWithNew(Map<? extends String, ? extends IWrapper> m);
    public IWrapper put(String key, Object value);
    

}
