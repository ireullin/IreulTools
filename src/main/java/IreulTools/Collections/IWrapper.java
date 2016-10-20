package IreulTools.Collections;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface IWrapper {
    public String toString();
    public String toString(String defaultVal);

    public int toInt();
    public int toInt(int defaultVal);

    public float toFloat();
    public float toFloat(float defaultVal);

    public long toLong();
    public long toLong(long defaultVal);

    public double toDouble();
    public double toDouble(double defaultVal);

    public Object value();

    public boolean isNull();
    public boolean isEmptyOrNull();

    public boolean equals(Object o);
    public int hashCode();
}
