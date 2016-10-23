package IreulTools.stringExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class Join implements IJoin{

    private static final Logger LOG = LoggerFactory.getLogger(Join.class);

    private String symbol = "";
    private List<String> buff = new ArrayList<String>(100);

    private Join(){
    }

    public static IJoin from(String s){
        return (new Join()).and(s);
    }

    public static IJoin from(Object o){
        return (new Join()).and(o);
    }

    public static IJoin from(String[] arr){
        return (new Join()).and(arr);
    }

    public static IJoin from(List<String> list){
        return (new Join()).and(list);
    }

    @Override
    public IJoin with(String symbol){
        this.symbol = symbol;
        return this;
    }

    @Override
    public IJoin and(String s){
        this.buff.add(s);
        return this;
    }

    @Override
    public IJoin and(Object o){
        this.buff.add(o.toString());
        return this;
    }

    @Override
    public IJoin and(String[] arr){
        for(String s: arr){
            this.buff.add(s);
        }
        return this;
    }

    @Override
    public IJoin and(List<String> list){
        this.buff.addAll(list);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(500);
        for(int i=0; i<buff.size(); i++){
            sb.append(buff.get(i));
            if(i<buff.size()-1)
                sb.append(this.symbol);
        }
        return sb.toString();
    }
}
