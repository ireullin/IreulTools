package IreulTools.StringExtension;

import IreulTools.JSON.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/10/19.
 */
public class Joiner {

    private static final Logger LOG = LoggerFactory.getLogger(Joiner.class);

    private String symbol = "";
    private List<String> buff = new ArrayList<String>(100);

    private Joiner(){
    }

    public static Joiner create(){
        return new Joiner();
    }

    public Joiner with(String s){
        this.symbol = s;
        return this;
    }

    public Joiner put(Object s){
        this.buff.add(s.toString());
        return this;
    }

    public Joiner put(String[] ss){
        for(String s: ss){
            this.buff.add(s);
        }
        return this;
    }

    public Joiner put(List<String> arr){
        this.buff.addAll(arr);
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
