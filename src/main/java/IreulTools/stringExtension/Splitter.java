package IreulTools.stringExtension;

import IreulTools.collections.ISimpleMap;
import IreulTools.collections.IWrapper;
import IreulTools.collections.Wrapper;
import IreulTools.functionalProgramming.IEachPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ireullin on 2016/10/22.
 */
public class Splitter implements ISplitter{

    private static final Logger LOG = LoggerFactory.getLogger(Splitter.class);

    private final String target;
    private final List<Character> delims = new ArrayList<Character>(10);
    private final Wrapper wrapper = Wrapper.create();

    private Splitter(String target){
        this.target = target;
    }

    public static ISplitter from(String target){
        return new Splitter(target);
    }

    @Override
    public ISplitter putDelim(char[] chars) {
        for(char c : chars) {
            delims.add(c);
        }
        return this;
    }

    @Override
    public ISplitter putDelim(List<Character> chars) {
        delims.addAll(chars);
        return this;
    }

    @Override
    public ISplitter putDelim(char c) {
        delims.add(c);
        return this;
    }

    @Override
    public void each(IEachPair<IWrapper, ISplitterInfo> pair) {
        int startAt = 0;
        int endAt = 0;
        int index = 0;
        for(int i=0; i<target.length(); i++){
            endAt = i;
            char curr = target.charAt(i);
            if(isInDelims(curr)){
//                LOG.debug("{} is in",curr);
                String sub = target.substring(startAt,endAt);
//                LOG.debug("sub={}", sub);
                SplitterInfo info = new SplitterInfo(startAt, endAt-1, index);
                boolean isCon = pair.isContinue(wrapper.setValue(sub),info);
                startAt = i+1;
                index+=1;
                if(!isCon){
                    return;
                }
            }
            else{
//                LOG.debug("{} is not in",curr);
            }
        }
        endAt += 1;
        String sub = target.substring(startAt,endAt);
        SplitterInfo info = new SplitterInfo(startAt, endAt, index);
        pair.isContinue(wrapper.setValue(sub),info);
    }

    public List<IWrapper> toList(){
        List<IWrapper> arr = new ArrayList<IWrapper>(20);
        int startAt = 0;
        int endAt = 0;
        for(int i=0; i<target.length(); i++){
            endAt = i;
            char curr = target.charAt(i);
            if(isInDelims(curr)){
                String sub = target.substring(startAt,endAt);
                arr.add( Wrapper.create().setValue(sub) );
                startAt = i+1;
            }
            else{
//                LOG.debug("{} is not in",curr);
            }
        }
        endAt += 1;
        String sub = target.substring(startAt,endAt);
        arr.add(Wrapper.create().setValue(sub));
        return arr;
    }

    private boolean isInDelims(char c){
        for(char delim : delims){
            if(c==delim)
                return true;
        }
        return false;
    }


}
