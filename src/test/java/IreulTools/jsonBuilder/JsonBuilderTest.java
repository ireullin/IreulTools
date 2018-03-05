package IreulTools.jsonBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class JsonBuilderTest extends TestCase{

    private static final Logger LOG = LoggerFactory.getLogger(JsonBuilderTest.class);



    public String genBuilder() throws Exception{
        IJsonMap node = JsonMap.create()
                .put("key1", 1)
                .put("key2", "2")
                .tap(LOG::info)
                .put("key3", 3.14159);

        IJsonList arr1 = JsonList.create()
                .put("row1")
                .put("row2")
                .tap(LOG::info)
                .put(node);

        IJsonList arr2 = JsonList.create()
                .merge(arr1)
                .put("row3")
                .tap(LOG::info)
                .put("row4");


        IJsonList arr3 = JsonList.create()
                .put("row5")
                .put("row6")
                .tap(LOG::info)
                .put(arr2);

        IJsonMap root = JsonMap.create()
                .put("key4", 4)
                .put("key5", "5")
                .put("key6", 6.14159)
                .tap(LOG::info)
                .put("arr3", arr3);

        return root.toString();
    }

    public String genAnswer() throws Exception{
        Map<String,Object> node = new TreeMap<String,Object>();
        node.put("key1", 1);
        node.put("key2", "2");
        node.put("key3", 3.14159);

        List<Object> arr1 = new ArrayList<Object>();
        arr1.add("row1");
        arr1.add("row2");
        arr1.add(node);

        List<Object> arr2 = new ArrayList<Object>();
        arr2.addAll(arr1);
        arr2.add("row3");
        arr2.add("row4");

        List<Object> arr3 = new ArrayList<Object>();
        arr3.add("row5");
        arr3.add("row6");
        arr3.add(arr2);

        Map<String,Object> root = new TreeMap<String,Object>();
        root.put("key4", 4);
        root.put("key5", "5");
        root.put("key6", 6.14159);
        root.put("arr3", arr3);

        ObjectMapper json = new ObjectMapper();
        return json.writeValueAsString(root);
    }


    @Test
    public void testJsonBuilder(){

        try {
            String test = genBuilder();
            String ans = genAnswer();

            LOG.info(test.toString());
            LOG.info(ans.toString());
            Assert.assertEquals(test, ans);
        }
        catch (Exception e){
            LOG.error("testJsonBuilder failed", e);
        }
    }
}
