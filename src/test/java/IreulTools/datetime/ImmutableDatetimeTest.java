package IreulTools.datetime;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ireullin on 2016/10/22.
 */
public class ImmutableDatetimeTest extends TestCase {

     private static final Logger LOG = LoggerFactory.getLogger(ImmutableDatetimeTest.class);

    @Test
    public void testMain() {

        IDatetime dt1 = Datetime.now();
        IDatetime dt2 = dt1.year(2012).month(2).day(15).hour(6).min(10).sec(5).millis(200);
        LOG.info(dt1.toString());
        LOG.info(dt2.toString());

        IDatetime dt3 = new Datetime(2015,8,4,9,10,15,000);
        LOG.info(dt3.toString());
        LOG.info(dt3.beginOfDay().toString());

        LOG.info("{}", dt3.stamp());
    }


}
