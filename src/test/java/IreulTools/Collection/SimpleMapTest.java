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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class SimpleMapTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMapTest.class);

    @Test
    public void testZip() {

        try {
            String[] keys = {"key1", "key2", "key3"};
            String[] vals = {"val1", "val2", "val3", "val4"};

            ISimpleMap map = SimpleMap.create();
            map.zip(keys, keys);

            LOG.info(map.toString());
            LOG.info("test1 success");
        }
        catch (Exception e){
            LOG.error("test failed", e);
            Assert.assertEquals(true,false);
        }

        try {
            String[] keys = {"key1", "key2", "key3","key4"};
            String[] vals = {"val1", "val2", "val3"};

            ISimpleMap map = SimpleMap.create();
            map.zip(keys, vals);

            Assert.assertEquals(true,false);
        }
        catch (Exception e){
            LOG.info("test2 success", e);
        }

        try {
            String[] keys = {"key1", "key2", "key3","key4","key5"};
            String[] vals = {"val1", "val2", "val3"};

            ISimpleMap map = SimpleMap.create();
            map.zip(keys, vals, "empty");

            LOG.info(map.toString());
            LOG.info("test3 success");
        }
        catch (Exception e){
            LOG.error("test3 failed", e);
        }

    }

    
    @Test
    public void testPutAndTap() {

        ISimpleMap map = SimpleMap.create()
                .put("key1","1")
                .put("key2","2")
                .tap( x -> LOG.info(x)  )
                .put("key3",3.14)
                .put("key4","4.32")
                .put("key4",4)
                .put("key5", "5.699")
                .remove("key2");

        LOG.info(map.toString());

        map.foreach((k,v) -> {
            LOG.info(k);
            LOG.info("  "+v.toString());
            LOG.info("  "+v.toDouble(-1));
            LOG.info("  "+v.toInt(-1));
        });

        map.clear();
        LOG.info(map.toString());
    }



}



