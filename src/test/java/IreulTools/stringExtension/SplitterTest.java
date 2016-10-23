package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import IreulTools.collections.Wrapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ireullin on 2016/10/22.
 */

public class SplitterTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SplitterTest.class);

    @Test
    public void testMain() {

        ISplit splitter = Split.from("2016-03-18T9:28:13Z").with("-T:Z");
        List<String> answer = new ArrayList<>();
        answer.add("2016");
        answer.add("03");
        answer.add("18");
        answer.add("9");
        answer.add("28");
        answer.add("13");

        List<String> result = new ArrayList<String>();
        splitter.toList().forEach(x -> result.add(x.toString()));
        Assert.assertArrayEquals(result.toArray(), answer.toArray());

        splitter.each( (w,i) -> {
            LOG.info(w.toString());
            Assert.assertEquals(w.toString(), answer.get(i));
            return i!=2;
        });

    }
}
