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
public class ImmutableDatetime implements IDatetime{

    private static final Logger LOG = LoggerFactory.getLogger(ImmutableDatetime.class);

    public final Calendar calendar = new GregorianCalendar();

    public static IDatetime readFrom(String dtstr, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dtstr);
        TimeZone tz = sdf.getTimeZone();
        ImmutableDatetime dt = new ImmutableDatetime(date, tz);
        return dt;
    }

    public static IDatetime  now(){
        return new ImmutableDatetime();
    }

    public static IDatetime  zeroDay(){
        ImmutableDatetime dt = new ImmutableDatetime(0);
        return dt.toUTC();
    }

    public ImmutableDatetime(){
    }

    public ImmutableDatetime(TimeZone tz){
        calendar.setTimeZone(tz);
    }

    public ImmutableDatetime(Date dt){
        calendar.setTime(dt);
    }

    public ImmutableDatetime(Date dt, TimeZone tz){
        calendar.setTime(dt);
        calendar.setTimeZone(tz);
    }

    public ImmutableDatetime(IReadOnlyDatetime datetime){
        calendar.setTimeInMillis(datetime.stamp());
    }

    public ImmutableDatetime(long stamp){
        calendar.setTimeInMillis(stamp);
    }

    public ImmutableDatetime(long stamp, TimeZone tz){
        calendar.setTimeInMillis(stamp);
        calendar.setTimeZone(tz);
    }


    public ImmutableDatetime(int year, int month, int day){
        this(year,month,day,0,0,0,0);
    }

    public ImmutableDatetime(int year, int month, int day, TimeZone tz){
        this(year,month,day,0,0,0,0,tz);
    }

    public ImmutableDatetime(int year, int month, int day, int hour, int min, int sec, int millis){
        calendar.set( year, month-1, day, hour, min, sec);
        calendar.set(Calendar.MILLISECOND, millis);
    }

    public ImmutableDatetime(int year, int month, int day, int hour, int min, int sec, int millis, TimeZone tz){
        calendar.set( year, month-1, day, hour, min, sec);
        calendar.set(Calendar.MILLISECOND, millis);
        calendar.setTimeZone(tz);
    }


    @Override
    public int year() {
        return  calendar.get(Calendar.YEAR);
    }

    @Override
    public IDatetime year(int v) {
        return new ImmutableDatetime(v,month(),day(),hour(),min(),sec(),millis(), getTimeZone());
    }

    @Override
    public int month() {
        return calendar.get(Calendar.MONTH)+1;
    }

    @Override
    public IDatetime month(int v) {
        return new ImmutableDatetime(year(),v,day(),hour(),min(),sec(),millis(), getTimeZone());
    }

    @Override
    public int day() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public IDatetime day(int v) {
        return new ImmutableDatetime(year(),month(),v,hour(),min(),sec(),millis(), getTimeZone());
    }

    @Override
    public int hour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public IDatetime hour(int v) {
        return new ImmutableDatetime(year(),month(),day(),v,min(),sec(),millis(), getTimeZone());
    }

    @Override
    public int min() {
        return calendar.get(Calendar.MINUTE);
    }

    @Override
    public IDatetime min(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),v,sec(),millis(), getTimeZone());
    }

    @Override
    public int sec() {
        return calendar.get(Calendar.SECOND);
    }

    @Override
    public IDatetime sec(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),min(),v,millis(), getTimeZone());
    }

    @Override
    public int millis() {
        return calendar.get(Calendar.MILLISECOND);
    }

    @Override
    public IDatetime millis(int v) {
        return new ImmutableDatetime(year(),month(),day(),hour(),min(),sec(),v, getTimeZone());
    }

    @Override
    public IDatetime clone(){
        return new ImmutableDatetime(stamp(), getTimeZone());
    }

    @Override
    public IDatetime reset(IReadOnlyDatetime dt) {
        calendar.setTimeInMillis(dt.stamp());
        calendar.setTimeZone(dt.getTimeZone());
        return this;
    }

    @Override
    public long stamp(){
        return calendar.getTimeInMillis();
    }

    @Override
    public IDatetime toBeginOfDay() {
        return new ImmutableDatetime(year(),month(),day(),0,0,0,0, getTimeZone());
    }

    @Override
    public IDatetime toEndOfDay() {
        return new ImmutableDatetime(year(),month(),day(),23,59,59,999, getTimeZone());
    }

    @Override
    public IDatetime addOrSubDay(int v) {
        ImmutableDatetime dt = new ImmutableDatetime(this.stamp(), getTimeZone());
        dt.calendar.add(Calendar.DATE, v);
        return dt;
    }

    @Override
    public IDatetime addOrSubHour(int v) {
        ImmutableDatetime dt = new ImmutableDatetime(this.stamp(), getTimeZone());
        dt.calendar.add(Calendar.HOUR, v);
        return dt;
    }

    @Override
    public IDatetime addOrSubMin(int v) {
        ImmutableDatetime dt = new ImmutableDatetime(this.stamp(), getTimeZone());
        dt.calendar.add(Calendar.MINUTE, v);
        return dt;
    }

    @Override
    public IDatetime addOrSubSec(int v) {
        ImmutableDatetime dt = new ImmutableDatetime(this.stamp(), getTimeZone());
        dt.calendar.add(Calendar.SECOND, v);
        return dt;
    }

    @Override
    public IDatetime addOrSubMillis(long v) {
        ImmutableDatetime dt = new ImmutableDatetime(this.stamp(), getTimeZone());
        dt.calendar.add(Calendar.MILLISECOND, (int)v);
        return dt;
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

        ImmutableDatetime datetime = (ImmutableDatetime) o;
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
    public IDatetime toTimeZone(TimeZone tz){
        return new ImmutableDatetime(stamp(), tz);
    }

    @Override
    public TimeZone getTimeZone(){
        return calendar.getTimeZone();
    }

    @Override
    public IDatetime toUTC(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        return new ImmutableDatetime(stamp(),tz);
    }

    @Override
    public IDatetime toLocalTime(){
        TimeZone tz = TimeZone.getDefault();
        return new ImmutableDatetime(stamp(),tz);
    }
}
