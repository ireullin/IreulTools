package IreulTools.collections;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class Wrapper implements IWrapper{

    private Object value;

    private Wrapper(Object obj){
        this.value = obj;
    }

    public static IWrapper of(Object obj){
        return new Wrapper(obj);
    }

    public static IWrapper ofNull(){
        return new Wrapper(null);
    }

    @Override
    public IWrapper reset(Object value){
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String defaultVal) {
        if(this.value==null)
            return defaultVal;
        else
            return this.value.toString();
    }

    @Override
    public int toInt() throws NullPointerException,NumberFormatException{
        return Integer.parseInt(value.toString());
    }

    @Override
    public int toInt(int defaultVal) {
        try {return toInt();}
        catch (Exception e){return defaultVal;}
    }

    @Override
    public float toFloat() throws NullPointerException,NumberFormatException{
        return Float.parseFloat(value.toString());
    }

    @Override
    public float toFloat(float defaultVal) {
        try {return toFloat();}
        catch (Exception e){return defaultVal;}
    }

    @Override
    public long toLong() throws NullPointerException,NumberFormatException{
        return Long.parseLong(value.toString());
    }

    @Override
    public long toLong(long defaultVal) {
        try {return toLong();}
        catch (Exception e){return defaultVal;}
    }

    @Override
    public double toDouble() throws NullPointerException,NumberFormatException{
        return Double.parseDouble(value.toString());
    }

    @Override
    public double toDouble(double defaultVal) {
        try {return toDouble();}
        catch (Exception e){return defaultVal;}
    }

    @Override
    public boolean toBoolean() throws NullPointerException,NumberFormatException{
        return Boolean.parseBoolean(value.toString());
    }

    @Override
    public boolean toBoolean(boolean defaultVal) {
        try {return toBoolean();}
        catch (Exception e){return defaultVal;}
    }

    @Override
    public Object value(){
        return this.value;
    }

    @Override
    public boolean isNull() {
        return value == null;
    }

    @Override
    public boolean isEmptyOrNull() {
        if(value==null)
            return true;

        if(value.toString().trim().length()==0)
            return true;

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(isNull())
            return false;

        return this.value.equals(o);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
