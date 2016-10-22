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
        LOG.info(dt3.setBeginOfDay().toString());

        LOG.info("{}", dt3.stamp());

        IDatetime dt4 = new Datetime(dt3.stamp());
        LOG.info(dt4.toString());

        try {
            IDatetime dt5 = Datetime.readFrom("20140825130972321", "yyyyMMddHHmmssSSS");
            LOG.info(dt5.toString());
        }
        catch (Exception e){
            LOG.error("format error",e);
        }

        try{
            IReadOnlyDatetime dt6 = Datetime.readFrom("2010-03-01 10:00:00","yyyy-MM-dd HH:mm:ss") .tap( dt -> LOG.info("Started at"+dt.toString()) )
                .addOrSubDay(-3).tap( dt -> LOG.info("sub {} days is {}", 3 ,  dt.toString()) )
                .addOrSubHour(73).tap( dt -> LOG.info("add {} hours is {}", 73 ,  dt.toString()) )
                .addOrSubMin(-120).tap( dt -> LOG.info("sub {} minutes is {}", 120 ,  dt.toString()) )
                .addOrSubSec(5).tap( dt -> LOG.info("add {} second is {}", 5 ,  dt.toString()) )
                .addOrSubMillis(1500).tap( dt -> LOG.info("add {} millisecond is {}", 1500 ,  dt.toString()) );
        }
        catch (Exception e){
            LOG.error("format error",e);
        }


    }


}
