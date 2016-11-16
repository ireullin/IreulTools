package IreulTools.datetime;

import IreulTools.functionalProgramming.ITap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * Created by ireullin on 2016/10/22.
 */
public class Datetime implements IDatetime{

    private static final Logger LOG = LoggerFactory.getLogger(Datetime.class);

    public final Calendar calendar = new GregorianCalendar();

    public Datetime(){
    }

    public Datetime(Date dt){
        calendar.setTime(dt);
    }

    public Datetime(long stamp){
        calendar.setTimeInMillis(stamp);
    }

    public Datetime(int year, int month, int day, int hour, int min, int sec, int millis){
        calendar.set( year, month-1, day, hour, min, sec);
        calendar.set(Calendar.MILLISECOND, millis);
    }

    public static IDatetime readFrom(String dtstr, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dtstr);
        TimeZone tz = sdf.getTimeZone();
        Datetime dt = new Datetime(date);
        dt.setTimeZone(tz);
        return dt;
    }

    public static IDatetime  now(){
        return new Datetime();
    }

    @Override
    public int year() {
        return  calendar.get(Calendar.YEAR);
    }

    @Override
    public IDatetime year(int v) {
        calendar.set(Calendar.YEAR, v);
        return this;
    }

    @Override
    public IDatetime month(int v) {
        calendar.set(Calendar.MONTH, v-1);
        return this;
    }

    @Override
    public int month() {
        return calendar.get(Calendar.MONTH)+1;
    }

    @Override
    public IDatetime day(int v) {
        calendar.set(Calendar.DAY_OF_MONTH, v);
        return this;
    }

    @Override
    public int day() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public IDatetime hour(int v) {
        calendar.set(Calendar.HOUR_OF_DAY, v);
        return this;
    }

    @Override
    public int hour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public IDatetime min(int v) {
        calendar.set(Calendar.MINUTE, v);
        return this;
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
        calendar.set(Calendar.SECOND, v);
        return this;
    }

    @Override
    public int millis() {
        return calendar.get(Calendar.MILLISECOND);
    }

    @Override
    public IDatetime millis(int v) {
        calendar.set(Calendar.MILLISECOND, v);
        return this;
    }

    @Override
    public IDatetime clone(){
        return new Datetime(year(),month(),day(),hour(),min(),sec(),millis());
    }

    @Override
    public IDatetime reset(IDatetime dt) {
        this.year(dt.year())
            .month(dt.month())
            .day(dt.day())
            .hour(dt.hour())
            .min(dt.min())
            .sec(dt.sec())
            .millis(dt.millis());

        return this;
    }

    @Override
    public long stamp(){
        return calendar.getTimeInMillis();
    }

    @Override
    public IDatetime setBeginOfDay() {
        return this.hour(0).min(0).sec(0).millis(0);
    }



    @Override
    public IDatetime addOrSubDay(double v) {
        return addOrSubHour(v*24);
    }

    @Override
    public IDatetime addOrSubHour(double v) {
        return addOrSubMin(v*60);
    }

    @Override
    public IDatetime addOrSubMin(double v) {
        return addOrSubSec(v*60);
    }

    @Override
    public IDatetime addOrSubSec(double v) {
        return addOrSubMillis(Math.round(v*1000));
    }

    @Override
    public IDatetime addOrSubMillis(long v) {
        calendar.add(Calendar.MILLISECOND, (int)v);
        return this;
    }

    @Override
    public String toString() {
        try {
            return toString("yyyy-MM-dd HH:mm:ss.SSS");
        }
        catch (Exception e){
            LOG.warn("output date failed",e);
            return "";
        }
    }


    public String toString(String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(getTimeZone());
        return sdf.format(calendar.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Datetime datetime = (Datetime) o;
        return datetime.stamp()==this.stamp();
    }

    @Override
    public int hashCode() {
        return calendar != null ? calendar.hashCode() : 0;
    }

    @Override
    public IDatetime tap(ITap<IReadOnlyDatetime> debugMsg) {
        debugMsg.put(this);
        return this;
    }

    @Override
    public IDuration during(IReadOnlyDatetime dt) {
        return new Duration(dt.stamp()-this.stamp());
    }

    @Override
    public IDatetime setTimeZone(TimeZone tz){
        calendar.setTimeZone(tz);
        return this;
    }

    @Override
    public TimeZone getTimeZone(){
        return calendar.getTimeZone();
    }

    @Override
    public IDatetime toUTC(){
        Datetime dt = new Datetime(stamp());
        dt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dt;
    }

    @Override
    public IDatetime toLocalTime(){
        Datetime dt = new Datetime(stamp());
        dt.setTimeZone(TimeZone.getDefault());
        return dt;
    }
}
