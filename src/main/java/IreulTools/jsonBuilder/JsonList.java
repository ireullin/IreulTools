package IreulTools.jsonBuilder;

import IreulTools.functionalProgramming.ITap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tech0039 on 2016/10/19.
 */
public class JsonList implements IJsonList{

    private static final Logger LOG = LoggerFactory.getLogger(JsonMap.class);

    private final List<Object> list;

    protected JsonList(){
        this.list = new ArrayList<Object>(100);
    }

    protected JsonList(List<Object> list){
        this.list = list;
    }

    public static JsonList create(){
        return new JsonList();
    }

    public static JsonList createWithList(List<Object> list){
        return new JsonList(list);
    }

    public IJsonList merge(List<?> val){
        this.list.addAll(val);
        return this;
    }

    public IJsonList merge(IJsonList val){
        this.list.addAll(val.toList());
        return this;
    }

    @Override
    public IJsonList put(Object val) {
        if(val instanceof IJsonList){
            this.list.add(((IJsonList)val).toList());
        }
        else if(val instanceof IJsonMap) {
            this.list.add(((IJsonMap)val).toMap());
        }
        else{
            this.list.add(val);
        }
        return this;
    }

    public List<Object> toList(){
        return this.list;
    }

    public ObjectNode toJsonNode() {
        ObjectMapper json = new ObjectMapper();
        return json.valueToTree(this.list);
    }

    @Override
    public String toString() {
        try {
            ObjectMapper json = new ObjectMapper();
            return json.writeValueAsString(this.list);
        }
        catch (Exception e){
            String msg = "convert to string failed";
            LOG.warn(msg,e);
            return "";
        }
    }

    @Override
    public IJsonList tap(ITap<String> debugMsg) throws Exception{
        debugMsg.put(toString());
        return this;
    }

    @Override
    public int size() {
        return this.list.size();
    }
}
