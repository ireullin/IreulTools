package IreulTools.jsonBuilder;

import IreulTools.collections.IWrapper;
import IreulTools.collections.Wrapper;
import IreulTools.functionalProgramming.ITap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class JsonMap implements IJsonMap {

    private static final Logger LOG = LoggerFactory.getLogger(JsonMap.class);

    private final Map<String,Object> map;

    protected JsonMap(){
        this.map = new TreeMap<String,Object>();
    }

    protected JsonMap(Map<String,Object> map){
        this.map = map;
    }

    public static IJsonMap create(){
        return new JsonMap();
    }

    public static IJsonMap createWithMap(Map<String,Object> map){
        return new JsonMap(map);
    }

    public IJsonMap put(String key, String val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, int val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, long val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, float val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, double val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, List<Object> val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, Map<String,Object> val) {
        this.map.put(key,val);
        return this;
    }

    public IJsonMap put(String key, IJsonMap val) {
        this.map.put(key,val.toMap());
        return this;
    }

    public IJsonMap put(String key, IJsonList val) {
        this.map.put(key,val.toList());
        return this;
    }

    @Override
    public IJsonMap put(String key, JsonNode val) {
        this.map.put(key,val);
        return this;
    }

    @Override
    public IJsonMap put(String key, ArrayNode val) {
        this.map.put(key,val);
        return this;
    }

    public Map<String, Object> toMap() {
        return this.map;
    }

    public ObjectNode toJsonNode() {
        ObjectMapper json = new ObjectMapper();
        return json.valueToTree(this.map);
    }

    @Override
    public String toString() {
        try {
            ObjectMapper json = new ObjectMapper();
            return json.writeValueAsString(this.map);
        }
        catch (Exception e){
            String msg = "convert to string failed";
            LOG.warn(msg,e);
            return "";
        }
    }

    @Override
    public IJsonMap tap(ITap<String> debugMsg) {
        debugMsg.put(toString());
        return this;
    }

    @Override
    public int size() {
        return this.map.size();
    }
}
