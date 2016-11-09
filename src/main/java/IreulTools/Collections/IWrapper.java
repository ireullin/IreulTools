package IreulTools.collections;

/**
 * Created by tech0039 on 2016/10/19.
 */
public interface IWrapper {
    public String toString();
    public String toString(String defaultVal);

    public int toInt();
    public int toInt(int defaultVal);

    public float toFloat() throws NullPointerException,NumberFormatException;
    public float toFloat(float defaultVal);

    public long toLong() throws NullPointerException,NumberFormatException;
    public long toLong(long defaultVal);

    public double toDouble() throws NullPointerException,NumberFormatException;
    public double toDouble(double defaultVal);

    public boolean toBoolean() throws NullPointerException,NumberFormatException;
    public boolean toBoolean(boolean defaultVal);

    public Object value();

    public boolean isNull();
    public boolean isEmptyOrNull();

    public boolean equals(Object o);
    public int hashCode();
}
