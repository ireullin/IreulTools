package IreulTools.datetime;

import IreulTools.functionalProgramming.ITap;

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

    public IDatetime addOrSubDay(double v);
    public IDatetime addOrSubHour(double v);
    public IDatetime addOrSubMin(double v);
    public IDatetime addOrSubSec(double v);
    public IDatetime addOrSubMillis(long v);
    public IDatetime setBeginOfDay();
    public IDatetime clone();
    public IDatetime tap(ITap<IReadOnlyDatetime> debugMsg);

    public IDuration during(IReadOnlyDatetime dt);
}
