package IreulTools.jsonBuilder;

import IreulTools.collections.IWrapper;
import IreulTools.functionalProgramming.ITap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface IJsonMap {
    public IJsonMap put(String key, String val);
    public IJsonMap put(String key, int val);
    public IJsonMap put(String key, long val);
    public IJsonMap put(String key, float val);
    public IJsonMap put(String key, double val);
    public IJsonMap put(String key, List<Object> val);
    public IJsonMap put(String key, Map<String,Object> val);
    public IJsonMap put(String key, IJsonMap val);
    public IJsonMap put(String key, IJsonList val);
    public IJsonMap put(String key, JsonNode val);
    public IJsonMap put(String key, ArrayNode val);
    public String toString();
    public ObjectNode toJsonNode();
    public Map<String,Object> toMap();
    public IJsonMap tap(ITap<String> debugMsg);
    public int size();
}
