package IreulTools.StringExtension;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class JoinerTest extends TestCase {


    private static final Logger LOG = LoggerFactory.getLogger(JoinerTest.class);

    @Test
    public void testMain() {

        String[] data1 = {"a","b","c","d"};
        List<String> data2 = new ArrayList<String>();
        data2.add("e");
        data2.add("f");
        data2.add("g");
        data2.add("h");

        Joiner joiner = Joiner.create().put(data1).put(data2).put("i").put("j").put(3).put(3.14);

        LOG.info(joiner.toString());
        String answer1 = "abcdefghij33.14";
        Assert.assertEquals(joiner.toString(), answer1);

        joiner.with(":");
        LOG.info(joiner.toString());
        String answer2 = "a:b:c:d:e:f:g:h:i:j:3:3.14";
        Assert.assertEquals(joiner.toString(), answer2);
    }
}
