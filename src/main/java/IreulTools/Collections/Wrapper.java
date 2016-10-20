package IreulTools.Collections;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class Wrapper implements IWrapper{

    private String value = null;


    private Wrapper(Object v){
        this.value = v.toString();
    }

    private Wrapper(){
    }

    public static IWrapper create(Object v){
        return new Wrapper(v);
    }

    public static IWrapper createNull(){
        return new Wrapper();
    }

    public String toString() {
        return toString("");
    }

    public String toString(String defaultVal) {
        if(this.value==null)
            return defaultVal;
        else
            return this.value;
    }

    public int toInt() {
        return Integer.parseInt(value);
    }

    public int toInt(int defaultVal) {
        try {return toInt();}
        catch (Exception e){return defaultVal;}
    }

    public float toFloat() {
        return Float.parseFloat(value);
    }

    public float toFloat(float defaultVal) {
        try {return toFloat();}
        catch (Exception e){return defaultVal;}
    }

    public long toLong() {
        return Long.parseLong(value);
    }

    public long toLong(long defaultVal) {
        try {return toLong();}
        catch (Exception e){return defaultVal;}
    }

    public double toDouble() {
        return Double.parseDouble(value);
    }

    public double toDouble(double defaultVal) {
        try {return toDouble();}
        catch (Exception e){return defaultVal;}
    }

    public boolean isNull() {
        return value == null;
    }

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
