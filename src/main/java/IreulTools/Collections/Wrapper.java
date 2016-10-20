package IreulTools.Collections;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class Wrapper implements IWrapper{

    private Object value = null;


    private Wrapper(){
    }

    public static Wrapper create(){
        return new Wrapper();
    }

    public IWrapper setValue(Object v){
        this.value = v;
        return this;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String defaultVal) {
        if(this.value==null)
            return defaultVal;
        else
            return this.value.toString();
    }

    public int toInt() {
        return Integer.parseInt(value.toString());
    }

    public int toInt(int defaultVal) {
        try {return toInt();}
        catch (Exception e){return defaultVal;}
    }

    public float toFloat() {
        return Float.parseFloat(value.toString());
    }

    public float toFloat(float defaultVal) {
        try {return toFloat();}
        catch (Exception e){return defaultVal;}
    }

    public long toLong() {
        return Long.parseLong(value.toString());
    }

    public long toLong(long defaultVal) {
        try {return toLong();}
        catch (Exception e){return defaultVal;}
    }

    public double toDouble() {
        return Double.parseDouble(value.toString());
    }

    public double toDouble(double defaultVal) {
        try {return toDouble();}
        catch (Exception e){return defaultVal;}
    }

    public Object value(){
        return this.value;
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
