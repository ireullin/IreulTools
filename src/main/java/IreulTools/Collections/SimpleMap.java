package IreulTools.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class SimpleMap implements ISimpleMap{

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMap.class);

    private static final IWrapper nullObj = Wrapper.createNull();

    private final Map<String, IWrapper> map = new TreeMap<String, IWrapper>();

    public SimpleMap(){
    }

    public ISimpleMap zip(Object[] keys, Object[] vals){
        return this.zip(keys, vals, null);
    }

    public ISimpleMap zip(Object[] keys, Object[] vals, Object defaultVal){
        for(int i=0; i< keys.length; i++){
            if(i>=vals.length){
                this.map.put(keys[i].toString(), Wrapper.create(defaultVal) );
            }
            else {
                this.map.put(keys[i].toString(), Wrapper.create(vals[i]));
            }
        }
        return this;
    }

    public ISimpleMap mergeWithOld(Map<? extends String, ? extends IWrapper> m) {
        for( Entry<? extends String, ? extends IWrapper> e : m.entrySet()){
            if(this.map.containsKey(e.getKey())){
                continue;
            }
            else{
                this.map.put(e.getKey(), e.getValue());
            }
        }
        return this;
    }

    public ISimpleMap mergeWithNew(Map<? extends String, ? extends IWrapper> m) {
        this.map.putAll(m);
        return this;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.map.containsKey(value);
    }

    public IWrapper get(Object key) {
        if(containsKey(key))
            return nullObj;
        else
            return this.map.get(key);
    }

    public IWrapper put(String key, IWrapper value) {
        return this.map.put(key,value);
    }

    public IWrapper put(String key, Object value) {
        return this.map.put(key, Wrapper.create(value) );
    }

    public IWrapper remove(Object key) {
        return this.map.remove(key);
    }

    public void putAll(Map<? extends String, ? extends IWrapper> m) {
        this.map.putAll(m);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<IWrapper> values() {
        return this.map.values();
    }

    public Set<Entry<String, IWrapper>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("{");
        for(Map.Entry<String,IWrapper> e: this.map.entrySet()){
            sb.append(" \"");
            sb.append(e.getKey());
            sb.append("\" : \"");
            sb.append(e.getValue().toString());
            sb.append("\" ,");
        }
        sb.deleteCharAt( sb.length()-1 );
        sb.append("}");
        return sb.toString();
    }
}
