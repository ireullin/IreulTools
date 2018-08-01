package IreulTools.jsonBuilder;


import IreulTools.functionalProgramming.ITap;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface IJsonList {
    public IJsonList merge(List<?> val);
    public IJsonList merge(IJsonList val);
    public IJsonList put(Object val);
    public String toString();
    public ObjectNode toJsonNode();
    public List<Object> toList();
    public IJsonList tap(ITap<String> debugMsg) throws Exception;
    public int size();
}
