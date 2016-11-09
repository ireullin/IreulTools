package IreulTools.collection;

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
    public void testExample1() {

        IWrapper sample1 = Wrapper.ofNull();
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
    public void testExample2() {

        IWrapper sample2 = Wrapper.of(2.331);
        Assert.assertEquals(sample2.toLong(3), 3);
        Assert.assertEquals(sample2.toInt(2), 2);
        Assert.assertEquals(sample2.toString(), "2.331");

        try{
            sample2.toInt();
            assertEquals(true,false);
        }
        catch (Exception e){
            LOG.info(e.getMessage(),e);
            assertEquals(true,true);
        }


        double epsilon = 1e9;
        float fDiff = sample2.toFloat() - 2.311f;
        Assert.assertEquals(fDiff < epsilon, true );

        double dDiff = sample2.toDouble() - 2.311d;
        Assert.assertEquals(dDiff < epsilon, true );
    }

}
