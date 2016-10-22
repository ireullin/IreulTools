package IreulTools.datetime;

import java.sql.Timestamp;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface IDatetime {
    public int year();
    public int month();
    public int day();
    public int hour();
    public int min();
    public int sec();
    public int millis();

    public IDatetime year(int v);
    public IDatetime month(int v);
    public IDatetime day(int v);
    public IDatetime hour(int v);
    public IDatetime min(int v);
    public IDatetime sec(int v);
    public IDatetime millis(int v);
    public IDatetime clone();
    public IDatetime beginOfDay();
    public long stamp();

    public String strftime(String format) throws Exception;
    public String toString();
}
