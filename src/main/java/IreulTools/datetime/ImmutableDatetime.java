package IreulTools.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ireullin on 2016/10/22.
 */
public class ImmutableDatetime implements IDatetime{

    private static final Logger LOG = LoggerFactory.getLogger(ImmutableDatetime.class);

    public final Calendar calendar = Calendar.getInstance();

    public ImmutableDatetime(){
    }

    public ImmutableDatetime(int year, int month, int day, int hour, int min, int sec, int millis){
        calendar.set( year, month-1, day, hour, min, sec);
        calendar.set(Calendar.MILLISECOND, millis);
    }

    public static IDatetime  now(){
        return new ImmutableDatetime();
    }

    @Override
    public int year() {
        return  calendar.get(Calendar.YEAR);
    }

    @Override
    public IDatetime year(int v) {
//        return new ImmutableDatetime(year(),month(),day(),hour(),min(),sec(),millis());
        return new ImmutableDatetime(v,month(),day(),hour(),min(),sec(),millis());
    }

    @Override
    public IDatetime month(int v) {
        return new ImmutableDatetime(year(),v,day(),hour(),min(),sec(),millis());
    }

    @Override
    public int month() {
        return calendar.get(Calendar.MONTH)+1;
    }

    @Override
    public IDatetime day(int v) {
        return new ImmutableDatetime(year(),month(),v,hour(),min(),sec(),millis());
    }

    @Override
    public int day() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public IDatetime hour(int v) {
        return new ImmutableDatetime(year(),month(),day(),v,min(),sec(),millis());
    }

    @Override
    public int hour() {
        return calendar.get(Calendar.HOUR);
    }

    @Override
    public IDatetime min(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),v,sec(),millis());
    }

    @Override
    public int min() {
        return calendar.get(Calendar.MINUTE);
    }

    @Override
    public int sec() {
        return calendar.get(Calendar.SECOND);
    }

    @Override
    public IDatetime sec(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),min(),v,millis());
    }

    @Override
    public int millis() {
        return calendar.get(Calendar.MILLISECOND);
    }

    @Override
    public IDatetime millis(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),min(),sec(),v);
    }

    @Override
    public IDatetime clone(){
        return new ImmutableDatetime(year(),month(),day(),hour(),min(),sec(),millis());
    }

    @Override
    public long stamp(){
        return calendar.getTimeInMillis();
    }

    @Override
    public IDatetime beginOfDay() {
        return new ImmutableDatetime(year(),month(),day(),0,0,0,0);
    }

    public String strftime(String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    @Override
    public String toString() {
        try {
            return strftime("yyyy-MM-dd HH:mm:ss.SSS");
        }
        catch (Exception e){
            LOG.warn("output date failed",e);
            return "";
        }
    }
}
