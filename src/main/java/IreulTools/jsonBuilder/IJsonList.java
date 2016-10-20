package IreulTools.jsonBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface IJsonList {
    public IJsonList merge(List<Object> val);
    public IJsonList merge(IJsonList val);
    public IJsonList put(String val);
    public IJsonList put(int val);
    public IJsonList put(long val);
    public IJsonList put(float val);
    public IJsonList put(double val);
    public IJsonList put(List<Object> val);
    public IJsonList put(Map<String,Object> val);
    public IJsonList put(IJsonMap val);
    public IJsonList put(IJsonList val);
    public String toString();
    public ObjectNode toJsonNode();
    public List<Object> toList();
}
