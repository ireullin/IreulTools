package IreulTools.Collection;

import IreulTools.Collections.ISimpleMap;
import IreulTools.Collections.SimpleMap;
import IreulTools.Collections.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class SimpleMapTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMapTest.class);

    @Test
    public void testSample1() {

        try {
            String[] keys = {"key1", "key2", "key3"};
            String[] vals = {"val1", "val2", "val3", "val4"};

            ISimpleMap map = new SimpleMap();
            map.zip(keys, vals);

            LOG.info(map.toString());
        }
        catch (Exception e){
            LOG.error("test failed", e);
        }

    }

    @Test
    public void testSample2() {

        try {
            String[] keys = {"key1", "key2", "key3","key4"};
            String[] vals = {"val1", "val2", "val3"};

            ISimpleMap map = new SimpleMap();
            map.zip(keys, vals);

            Assert.assertEquals(true,false);
        }
        catch (Exception e){
            LOG.info("test success", e);
        }
    }

    @Test
    public void testSample3() {

        try {
            String[] keys = {"key1", "key2", "key3","key4","key5"};
            String[] vals = {"val1", "val2", "val3"};

            ISimpleMap map = new SimpleMap();
            map.zip(keys, vals, "empty");

            LOG.info(map.toString());
        }
        catch (Exception e){
            LOG.error("test failed", e);
        }
    }


    @Test
    public void testSample3() {

        Map<String,String> newMap = new TreeMap<String,String>();
        newMap.put("key1","new");
        newMap.put("key2","new");
        newMap.put("key3","new");

        Map<String,String> oldMap = new TreeMap<String,String>();
        oldMap.put("key3","old");
        oldMap.put("key4","old");
        oldMap.put("key5","old");


        ISimpleMap map = new SimpleMap();
        map.zip(keys, vals, "empty");

        LOG.info(map.toString());
    }



}

