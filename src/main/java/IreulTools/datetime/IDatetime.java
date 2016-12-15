package IreulTools.datetime;

import IreulTools.functionalProgramming.ITap;

import java.util.TimeZone;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface IDatetime extends IReadOnlyDatetime{

    public IDatetime year(int v);
    public IDatetime month(int v);
    public IDatetime day(int v);
    public IDatetime hour(int v);
    public IDatetime min(int v);
    public IDatetime sec(int v);
    public IDatetime millis(int v);

    public IDatetime addOrSubDay(int v);
    public IDatetime addOrSubHour(int v);
    public IDatetime addOrSubMin(int v);
    public IDatetime addOrSubSec(int v);
    public IDatetime addOrSubMillis(long v);

    /*
    It's the only method which changes inner value.
     */
    public IDatetime reset(IReadOnlyDatetime dt);

    public IDatetime clone();
    public IDatetime toBeginOfDay();
    public IDatetime toEndOfDay();
    public IDatetime toTimeZone(TimeZone tz);
    public IDatetime toUTC();
    public IDatetime toLocalTime();
    public IDatetime tap(ITap<IReadOnlyDatetime> debugMsg);

    /**
     * @deprecated use {@link #to()} instead.
     */
    @Deprecated
    public IDuration during(IReadOnlyDatetime dt);
    public IDuration to(IReadOnlyDatetime dt);
}
