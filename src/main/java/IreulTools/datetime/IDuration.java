package IreulTools.datetime;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface IDuration {

    public int dayPart();
    public int hourPart();
    public int minPart();
    public int secPart();
    public int millisPart();

    public double totalDay();
    public double totalHour();
    public double totalMin();
    public double totalSec();
    public long totalMillis();

    public long stamp();
}
