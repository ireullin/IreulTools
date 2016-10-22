package IreulTools.datetime;

import IreulTools.collections.ITap;

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
    public IDatetime addOrSubMillis(int v);
    public IDatetime setBeginOfDay();
    public IDatetime clone();
    public IDatetime tap(ITap<IReadOnlyDatetime> debugMsg);

    public IDuration during(IReadOnlyDatetime dt);
}
