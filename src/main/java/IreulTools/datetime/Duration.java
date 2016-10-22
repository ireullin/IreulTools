package IreulTools.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ireullin on 2016/10/22.
 */
public class Duration implements IDuration {

    private class Partial{
        public int days;
        public int hours;
        public int minutes;
        public int seconds;
        public int milliseconds;
        public boolean isAfter;
        public Partial(long stamp){
            stamp = Math.abs(stamp);
            this.days = (int)(stamp / (24*60*60*1000));
            int dayRem = (int)(stamp % (24*60*60*1000));
            this.hours =  dayRem / ((60*60*1000));
            int hoursRem = dayRem % ((60*60*1000));
            this.minutes = hoursRem / ((60*1000));
            int minRem = hoursRem % ((60*1000));
            this.seconds = minRem / 1000;
            this.milliseconds = minRem % 1000;
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(Duration.class);
    private long stamp;
    private Partial partial = null;

    public Duration(long stamp){
        this.stamp = stamp;
    }

    @Override
    public int dayPart() {
        if(partial==null) partial = new Partial(stamp);
        return partial.days;
    }

    @Override
    public int hourPart() {
        if(partial==null) partial = new Partial(stamp);
        return partial.hours;
    }

    @Override
    public int minPart() {
        if(partial==null) partial = new Partial(stamp);
        return partial.minutes;
    }

    @Override
    public int secPart() {
        if(partial==null) partial = new Partial(stamp);
        return partial.seconds;
    }

    @Override
    public int millisPart() {
        if(partial==null) partial = new Partial(stamp);
        return partial.milliseconds;
    }

    @Override
    public double totalDay() {
        return totalHour() / 24d;
    }

    @Override
    public double totalHour() {
        return totalMin() / 60d;
    }

    @Override
    public double totalMin() {
        return totalSec() / 60d;
    }

    @Override
    public double totalSec() {
        return totalMillis() / 1000d;
    }

    @Override
    public long totalMillis() {
        return this.stamp;
    }

    @Override
    public long stamp() {
        return this.stamp;
    }

    @Override
    public String toString(){
        return String.format("%d days, %d hours, %d minutes, %d seconds and %d milliseconds",
                dayPart(),
                hourPart(),
                minPart(),
                secPart(),
                millisPart()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duration duration = (Duration) o;
        return duration.stamp()==this.stamp();
    }

    @Override
    public int hashCode() {
        int result = (int) (stamp ^ (stamp >>> 32));
        result = 31 * result + (partial != null ? partial.hashCode() : 0);
        return result;
    }
}
