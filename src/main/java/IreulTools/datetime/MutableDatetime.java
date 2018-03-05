package IreulTools.datetime;

import IreulTools.functionalProgramming.ITap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MutableDatetime implements IDatetime {

    private static final Logger LOG = LoggerFactory.getLogger(MutableDatetime.class);

    private IDatetime immutableDatetime;

    public static IDatetime readFrom(String dtstr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dtstr);
        TimeZone tz = sdf.getTimeZone();
        MutableDatetime dt = new MutableDatetime(date, tz);
        return dt;
    }

    public static IDatetime now(){
        return new MutableDatetime();
    }

    public static IDatetime  zeroDay(){
        MutableDatetime dt = new MutableDatetime(0);
        return dt.toUTC();
    }

    private MutableDatetime(){
        this.immutableDatetime = ImmutableDatetime.now();
    }

    public static IDatetime of(TimeZone tz){
        return new MutableDatetime(tz);
    }

    private MutableDatetime(TimeZone tz){
        this.immutableDatetime = ImmutableDatetime.of(tz);
    }

    public static IDatetime of(Date dt){
        return new MutableDatetime(dt);
    }

    private MutableDatetime(Date dt){
        this.immutableDatetime = ImmutableDatetime.of(dt);
    }

    public static IDatetime of(Date dt, TimeZone tz){
        return new MutableDatetime(dt, tz);
    }

    private MutableDatetime(Date dt, TimeZone tz){
        this.immutableDatetime = ImmutableDatetime.of(dt, tz);
    }

    public static IDatetime of(IReadOnlyDatetime datetime){
        return new MutableDatetime(datetime);
    }

    private MutableDatetime(IReadOnlyDatetime datetime){
        this.immutableDatetime = ImmutableDatetime.of(datetime);
    }

    public static IDatetime of(long stamp){
        return new MutableDatetime(stamp);
    }

    private MutableDatetime(long stamp){
        this.immutableDatetime = ImmutableDatetime.of(stamp);
    }

    public static IDatetime of(long stamp, TimeZone tz){
        return new MutableDatetime(stamp, tz);
    }

    private MutableDatetime(long stamp, TimeZone tz){
        this.immutableDatetime = ImmutableDatetime.of(stamp, tz);
    }

    public static IDatetime of(int year, int month, int day){
        return new MutableDatetime(year,month,day,0,0,0,0);
    }

    public static IDatetime of(int year, int month, int day, TimeZone tz){
        return new MutableDatetime(year,month,day,0,0,0,0,tz);
    }

    public static IDatetime of(int year, int month, int day, int hour, int min, int sec, int millis) {
        return new MutableDatetime(year, month, day, hour, min, sec, millis);
    }

    public static IDatetime of(int year, int month, int day, int hour, int min, int sec, int millis, TimeZone tz){
        return new MutableDatetime(year, month, day, hour, min, sec, millis, tz);
    }

    private MutableDatetime(int year, int month, int day, int hour, int min, int sec, int millis){
        this.immutableDatetime = ImmutableDatetime.of(year, month, day, hour, min, sec, millis);
    }

    private MutableDatetime(int year, int month, int day, int hour, int min, int sec, int millis, TimeZone tz){
        this.immutableDatetime = ImmutableDatetime.of(year, month, day, hour, min, sec, millis, tz);
    }

    @Override
    public IDatetime year(int v) {
        this.immutableDatetime = this.immutableDatetime.year(v);
        return this;
    }

    @Override
    public IDatetime month(int v) {
        this.immutableDatetime = this.immutableDatetime.month(v);
        return this;
    }

    @Override
    public IDatetime day(int v) {
        this.immutableDatetime = this.immutableDatetime.day(v);
        return this;
    }

    @Override
    public IDatetime hour(int v) {
        this.immutableDatetime = this.immutableDatetime.hour(v);
        return this;
    }

    @Override
    public IDatetime min(int v) {
        this.immutableDatetime = this.immutableDatetime.min(v);
        return this;
    }

    @Override
    public IDatetime sec(int v) {
        this.immutableDatetime = this.immutableDatetime.sec(v);
        return this;
    }

    @Override
    public IDatetime millis(int v) {
        this.immutableDatetime = this.immutableDatetime.millis(v);
        return this;
    }

    @Override
    public IDatetime addOrSubDay(int v) {
        this.immutableDatetime = this.immutableDatetime.addOrSubDay(v);
        return this;
    }

    @Override
    public IDatetime addOrSubHour(int v) {
        this.immutableDatetime = this.immutableDatetime.addOrSubHour(v);
        return this;
    }

    @Override
    public IDatetime addOrSubMin(int v) {
        this.immutableDatetime = this.immutableDatetime.addOrSubMin(v);
        return this;
    }

    @Override
    public IDatetime addOrSubSec(int v) {
        this.immutableDatetime = this.immutableDatetime.addOrSubSec(v);
        return this;
    }

    @Override
    public IDatetime addOrSubMillis(long v) {
        this.immutableDatetime = this.immutableDatetime.addOrSubMillis(v);
        return this;
    }

    @Override
    public IDatetime reset(IReadOnlyDatetime dt) throws Exception {
        this.immutableDatetime = ImmutableDatetime.of(dt.stamp(), dt.getTimeZone());
        return this;
    }

    @Override
    public IDatetime clone() {
        return MutableDatetime.of(this.immutableDatetime);
    }

    @Override
    public IDatetime toBeginOfDay() {
        this.immutableDatetime = this.immutableDatetime.toBeginOfDay();
        return this;
    }

    @Override
    public IDatetime toEndOfDay() {
        this.immutableDatetime = this.immutableDatetime.toEndOfDay();
        return this;
    }

    @Override
    public IDatetime toTimeZone(TimeZone tz) {
        this.immutableDatetime = this.immutableDatetime.toTimeZone(tz);
        return this;
    }

    @Override
    public IDatetime toUTC() {
        this.immutableDatetime = this.immutableDatetime.toUTC();
        return this;
    }

    @Override
    public IDatetime toLocalTime() {
        this.immutableDatetime = this.immutableDatetime.toLocalTime();
        return this;
    }

    @Override
    public IDatetime tap(ITap<IReadOnlyDatetime> debugMsg) throws Exception {
        debugMsg.put(this);
        return this;
    }

    @Override
    public IDuration during(IReadOnlyDatetime dt) {
        return to(dt);
    }

    @Override
    public IDuration to(IReadOnlyDatetime dt) {
        return this.immutableDatetime.to(dt);
    }

    @Override
    public IDuration from(IReadOnlyDatetime dt) {
        return this.immutableDatetime.from(dt);
    }

    @Override
    public int year() {
        return this.immutableDatetime.year();
    }

    @Override
    public int month() {
        return this.immutableDatetime.month();
    }

    @Override
    public int day() {
        return this.immutableDatetime.day();
    }

    @Override
    public int hour() {
        return this.immutableDatetime.hour();
    }

    @Override
    public int min() {
        return this.immutableDatetime.min();
    }

    @Override
    public int sec() {
        return this.immutableDatetime.sec();
    }

    @Override
    public int millis() {
        return this.immutableDatetime.millis();
    }

    @Override
    public long stamp() {
        return this.immutableDatetime.stamp();
    }

    @Override
    public TimeZone getTimeZone() {
        return this.immutableDatetime.getTimeZone();
    }

    @Override
    public String toString(String format) throws Exception {
        return this.immutableDatetime.toString(format);
    }

    @Override
    public String toString(){
        return this.immutableDatetime.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutableDatetime datetime = (MutableDatetime) o;
        return datetime.stamp()==this.stamp();
    }
}
