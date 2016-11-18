package IreulTools.datetime;

import java.util.TimeZone;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface IReadOnlyDatetime {
    public int year();
    public int month();
    public int day();
    public int hour();
    public int min();
    public int sec();
    public int millis();
    public long stamp();
    public TimeZone timeZone();
    public String toString(String format) throws Exception;
}
