package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import IreulTools.collections.Wrapper;
import IreulTools.functionalProgramming.IEachPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ireullin on 2016/10/22.
 */
public class Split implements ISplit {

    private static final Logger LOG = LoggerFactory.getLogger(Split.class);

    private final String source;
    private String delims = " ";

    private Split(String source){
        this.source = source;
    }

    public static ISplit from(String source){
        return new Split(source);
    }

    @Override
    public ISplit with(String delims){
        this.delims = delims;
        return this;
    }

    @Override
    public void each(IEachPair<IWrapper, Integer> each) {
        int index = 0;
        StringTokenizer tk = new StringTokenizer(this.source, this.delims.toString());
        while (tk.hasMoreTokens()){
            String token = tk.nextToken();
            if(!each.isContinue(new Wrapper(token), index)){
                break;
            }
            index += 1;
        }
    }

    @Override
    public List<IWrapper> toList(){
        List<IWrapper> arr = new ArrayList<IWrapper>(20);
        each((w,i) -> {
            arr.add(w);
            return true;
        });
        return arr;
    }


}
