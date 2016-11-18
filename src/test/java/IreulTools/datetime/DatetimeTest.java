package IreulTools.datetime;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It's an immutable datetime class.
 */
public class DatetimeTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(DatetimeTest.class);


    @Test
    public void testZeroDay() {
        try {
            IDatetime dt1 = Datetime.zeroDay();
            LOG.info(dt1.timeZone().toString());
            LOG.info(dt1.toString());

            IDatetime dt2 = Datetime.readFrom("1970-01-01 08:00:00.000", "yyyy-MM-dd HH:mm:ss.SSS");
            LOG.info(dt2.timeZone().toString());
            LOG.info(dt2.toString());
            LOG.info(dt2.toUTC().toString());

            assertEquals(dt1.equals(dt2) , true);
        }
        catch (Exception e){
            LOG.error("error", e);
            assertEquals(false,true);
        }
    }


    @Test
    public void testReset() {
        try {
            String sample = "2016-08-04 13:34:56";
            IDatetime dt1 = Datetime.readFrom(sample, "yyyy-MM-dd HH:mm:ss");
            LOG.info(dt1.toString("yyyy-MM-dd HH:mm:ss a"));

            IDatetime dt2 = Datetime.now();
            LOG.info(dt2.toString("yyyy-MM-dd HH:mm:ss"));
            dt2.reset(dt1);
            LOG.info(dt2.toString("yyyy-MM-dd HH:mm:ss a"));
            assertEquals(dt2.toString("yyyy-MM-dd HH:mm:ss"), sample);
        }
        catch (Exception e){
            LOG.error("error", e);
            assertEquals(false,true);
        }
    }

    @Test
    public void testTimeZone() {
        try {
            IDatetime dt1 = Datetime.now();
            LOG.info("{} {}", dt1.toString("yyyy-MM-dd HH:mm:ss zzz "), dt1.timeZone());

            IDatetime dtutc = dt1.toUTC();
            LOG.info("{} {}", dtutc.toString("yyyy-MM-dd HH:mm:ss zzz "), dtutc.timeZone());
            LOG.info("{} {}", dt1.toString("yyyy-MM-dd HH:mm:ss zzz "), dt1.timeZone());

            IDatetime dt2 = dt1.toLocalTime();
            LOG.info("{} {}", dt2.toString("yyyy-MM-dd HH:mm:ss zzz "), dt2.timeZone());


            IDatetime dt3 = Datetime.readFrom("2016-10-25 16:07:21 TST", "yyyy-MM-dd HH:mm:ss zzz");
            LOG.info("{} {}", dt3.toString("yyyy-MM-dd HH:mm:ss zzz"), dt3.timeZone());

            //!!!!!!!!!! it will get CEST. I don't know why ????????
            IDatetime dt4 = Datetime.readFrom("2016-10-25 16:07:21 UTC", "yyyy-MM-dd HH:mm:ss zzz");
            LOG.info("{} {}", dt4.toString("yyyy-MM-dd HH:mm:ss zzz"), dt4.timeZone());

            IDatetime dt5 = Datetime.readFrom("2016-10-25 16:07:21", "yyyy-MM-dd HH:mm:ss");
            LOG.info("{} {}", dt5.toString("yyyy-MM-dd HH:mm:ss zzz"), dt5.timeZone());

        }
        catch (Exception e){
            LOG.error("error", e);
        }

    }

    @Test
    public void testMain() {

        IDatetime dt1 = Datetime.now();
        IDatetime dt2 = dt1.year(2012).month(2).day(15).hour(6).min(10).sec(5).millis(200);
        LOG.info(dt1.toString());
        LOG.info(dt2.toString());
        assertEquals(dt1.equals(dt2),false);

        IDatetime dt3 = new Datetime(2015,8,4,9,10,15,000);

        LOG.info(dt3.toString());
        assertEquals("2015-08-04 09:10:15.000", dt3.toString());

        IDatetime beginOfDt3 = dt3.setBeginOfDay();
        LOG.info(beginOfDt3.toString());
        assertEquals("2015-08-04 00:00:00.000", beginOfDt3.toString());

        LOG.info("{} stamp:{}", dt3.toString(), dt3.stamp());
        assertEquals(1438650615000l, dt3.stamp());

        LOG.info("==============================");

        IDatetime dt4 = new Datetime(dt3.stamp());
        LOG.info(dt4.toString());
        assertEquals(dt3.stamp(), dt4.stamp());
        LOG.info("==============================");

        try {
            IDatetime dt5 = Datetime.readFrom("20140825130972321", "yyyyMMddHHmmssSSS");
            LOG.info(dt5.toString());
            assertEquals("2014-08-25 13:10:12.321",dt5.toString());
        }
        catch (Exception e){
            LOG.error("format error",e);
            assertEquals(false,true);
        }
        LOG.info("==============================");

        try{
            IDatetime dt6 = Datetime.readFrom("2010-03-01 10:00:00","yyyy-MM-dd HH:mm:ss") .tap( dt -> LOG.info("Started at"+dt.toString()) )
                .addOrSubDay(-3).tap( dt -> LOG.info("sub {} days is {}", 3 ,  dt.toString()) )
                .addOrSubHour(73).tap( dt -> LOG.info("add {} hours is {}", 73 ,  dt.toString()) )
                .addOrSubMin(-120).tap( dt -> LOG.info("sub {} minutes is {}", 120 ,  dt.toString()) )
                .addOrSubSec(5).tap( dt -> LOG.info("add {} second is {}", 5 ,  dt.toString()) )
                .addOrSubMillis(1500).tap( dt -> LOG.info("add {} millisecond is {}", 1500 ,  dt.toString()) );

            LOG.info(dt6.toString());
            assertEquals("2010-03-01 09:00:06.500",dt6.toString());
        }
        catch (Exception e){
            LOG.error("format error",e);
        }
        LOG.info("==============================");

        IDatetime dtA = Datetime.now();
        IDatetime dtB = dtA.clone().addOrSubDay(-1)
                .addOrSubHour(2)
                .addOrSubMin(3)
                .addOrSubSec(5)
                .addOrSubMillis(600);

        IDuration du = dtA.during(dtB);
        LOG.info("A is {} ({}), B is {} ({})", dtA.toString(), dtA.stamp(), dtB.toString(), dtB.stamp());
        LOG.info("duration is {} ({})", du.toString(), du.stamp());
        LOG.info("total {} days", du.totalDay());
        LOG.info("total {} hours", du.totalHour());
        LOG.info("total {} minutes", du.totalMin());
        LOG.info("total {} seconds", du.totalSec());
        LOG.info("total {} milliseconds", du.totalMillis());

        IDatetime dtC = dtA.addOrSubMillis(du.totalMillis());
        assertEquals( dtC.stamp(), dtB.stamp() );
    }

}
