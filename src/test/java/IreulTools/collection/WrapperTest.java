package IreulTools.collection;

import IreulTools.collections.IWrapper;
import IreulTools.collections.ImmutableWrapper;
import IreulTools.collections.Wrapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class WrapperTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(WrapperTest.class);

    @Test
    public void testExample1() {

        IWrapper sample1 = Wrapper.ofNull();
        LOG.info("{}", sample1.toFloat(0.3f));
        assertEquals(sample1.isNull(), true);
        assertEquals(sample1.isEmptyOrNull(), true);
        assertEquals(sample1.toString("xxx"), "xxx");
        assertEquals(sample1.toString(), "");
        assertEquals(sample1.toInt(5), 5);
        assertEquals(sample1.toLong(5), 5l);

        try{
            sample1.toInt();
            assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            assertEquals(true,false);
        }

        try{
            sample1.toLong();
            assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            assertEquals(true,false);
        }

        try{
            sample1.toFloat();
            assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            assertEquals(true,false);
        }

        try{
            sample1.toDouble();
            assertEquals(true,false);
        }
        catch (NullPointerException  e){
            LOG.info("Success, I caught the exception");
        }
        catch (Exception  e){
            LOG.error("test failed", e);
            assertEquals(true,false);
        }

    }

    @Test
    public void testExample2() {

        IWrapper sample2 = Wrapper.of(2.331);
        assertEquals(sample2.toLong(3), 3);
        assertEquals(sample2.toInt(2), 2);
        assertEquals(sample2.toString(), "2.331");

        try{
            sample2.toInt();
            assertEquals(true,false);
        }
        catch (Exception e){
//            LOG.info(e.getMessage(),e);
            assertEquals(true,true);
        }


        double epsilon = 1e9;
        float fDiff = sample2.toFloat() - 2.311f;
        assertEquals(fDiff < epsilon, true );

        double dDiff = sample2.toDouble() - 2.311d;
        assertEquals(dDiff < epsilon, true );
    }

    @Test
    public void testExample3() throws Exception{
            IWrapper sample3 = Wrapper.ofNull();
            assertEquals(sample3.isNull(), true);

            sample3.reset("reset value");
            assertEquals(sample3.toString(), "reset value");
    }

    @Test
    public void testExample4() throws Exception{
        try {
            IWrapper sample4 = ImmutableWrapper.of(3);
            assertEquals(sample4.toString(), "3");
            sample4.reset("reset value");
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "This is immutable");
        }
    }

}
