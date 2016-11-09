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
public interface IJsonList {
    public IJsonList merge(List<Object> val);
    public IJsonList merge(IJsonList val);
    public IJsonList put(Object val);
    public String toString();
    public ObjectNode toJsonNode();
    public List<Object> toList();
    public IJsonList tap(ITap<String> debugMsg);
    public int size();
}
