package IreulTools.collections;

import IreulTools.functionalProgramming.IEachPair;
import IreulTools.functionalProgramming.ITap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface ISimpleMap{
    public ISimpleMap zip(Object[] keys, Object[] vals) throws Exception;
    public ISimpleMap zip(Object[] keys, Object[] vals, Object defaultVal);

    public ISimpleMap mergeAndStayOld(Map<? extends String, ? extends Object> m);
    public ISimpleMap mergeAndStayNew(Map<? extends String, ? extends Object> m);
    public ISimpleMap put(String key, Object value);
    public ISimpleMap remove(Object key);
    public ISimpleMap putAll(Map<? extends String, ? extends Object> m);
    public ISimpleMap clear();

    public ISimpleMap tap(ITap<String> debugMsg);
    public ISimpleMap each(IEachPair<String, IWrapper> f);

    public int size();
    public boolean isEmpty();
    public boolean containsKey(Object key);
    public boolean containsValue(Object value);

    public IWrapper get(Object key);
    public Set<String> keySet();
    public Collection<Object> values();
    public Set<Map.Entry<String, Object>> entrySet();
    public Map<String,Object> toMap();

}
