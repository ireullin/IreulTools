package IreulTools.collections;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final Wrapper wrapper = Wrapper.create();

    private final Map<String, Object> map;

    private SimpleMap(Map<String, Object> map){
        this.map = map;
    }

    public static ISimpleMap create(){
        return new SimpleMap(new TreeMap<String, Object>());
    }

    public static ISimpleMap create(Map<String, Object> otherMap){
        return new SimpleMap(otherMap);
    }

    public ISimpleMap zip(Object[] keys, Object[] vals) throws Exception{
        if(keys.length>vals.length) {
            throw new Exception("aaa");
        }

        return this.zip(keys, vals, null);
    }

    public ISimpleMap zip(Object[] keys, Object[] vals, Object defaultVal){
        for(int i=0; i< keys.length; i++){
            if(i>=vals.length){
                this.map.put(keys[i].toString(), defaultVal);
            }
            else {
                this.map.put(keys[i].toString(), vals[i]);
            }
        }
        return this;
    }

    public ISimpleMap mergeAndStayOld(Map<? extends String, ? extends Object> m) {
        for( Map.Entry<? extends String, ? extends Object> e : m.entrySet()){
            if(this.map.containsKey(e.getKey())){
                continue;
            }
            else{
                this.map.put(e.getKey(), e.getValue());
            }
        }
        return this;
    }

    public ISimpleMap mergeAndStayNew(Map<? extends String, ? extends Object> m) {
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
            return wrapper.setValue(null);
        else
            return wrapper.setValue(this.map.get(key));
    }

    public ISimpleMap put(String key, Object value) {
        this.map.put(key,value);
        return this;
    }

    public ISimpleMap remove(Object key) {
        this.map.remove(key);
        return this;
    }

    public ISimpleMap putAll(Map<? extends String, ? extends Object> m) {
        this.map.putAll(m);
        return this;
    }

    public ISimpleMap clear() {
        this.map.clear();
        return this;
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Map<String,Object> toMap(){
        return this.map;
    }

    public ISimpleMap foreach(IForeachMap<String, IWrapper> f){
        for(Map.Entry<String,Object> e : this.map.entrySet() ) {
            f.foreach(e.getKey(),wrapper.setValue( e.getValue() ));
        }
        return this;
    }

    public ISimpleMap tap(ITap<String> debugMsg){
        debugMsg.put(this.toString());
        return this;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper json = new ObjectMapper();
            return json.writeValueAsString(this.map);
        }
        catch(Exception e){
            return "";
        }
    }
}
