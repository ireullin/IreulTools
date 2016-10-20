package IreulTools.Collection;

import IreulTools.collections.IWrapper;
import IreulTools.collections.Wrapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class WrapperTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(WrapperTest.class);

    @Test
    public void testSample1() {

        IWrapper sample1 = Wrapper.create().setValue(null);
        LOG.info("{}", sample1.toFloat(0.3f));
        Assert.assertEquals(sample1.isNull(), true);
        Assert.assertEquals(sample1.isEmptyOrNull(), true);
        Assert.assertEquals(sample1.toString("xxx"), "xxx");
        Assert.assertEquals(sample1.toString(), "");
        Assert.assertEquals(sample1.toInt(5), 5);
        Assert.assertEquals(sample1.toLong(5), 5l);

        try{
            sample1.toInt();
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            Assert.assertEquals(true,false);
        }

        try{
            sample1.toLong();
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            Assert.assertEquals(true,false);
        }

        try{
            sample1.toFloat();
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            Assert.assertEquals(true,false);
        }

        try{
            sample1.toDouble();
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            Assert.assertEquals(true,false);
        }

    }


    @Test
    public void testSample2() {

        IWrapper sample2 = Wrapper.create().setValue(2.331);
        LOG.info("{}", sample2.toFloat());
        LOG.info("{}", sample2.toDouble());
        LOG.info("{}", sample2.toLong(3));
        LOG.info("{}", sample2.toInt(2));
        LOG.info("{}", sample2.toString());


    }

}
