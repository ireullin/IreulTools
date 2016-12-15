package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import IreulTools.functionalProgramming.IEachPair;
import IreulTools.functionalProgramming.IEachPairUntilEnd;

import java.util.List;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface ISplit {
    public ISplit with(String s);
    public void each(IEachPair<Boolean,Integer,IWrapper> f);
    public void eachUntilEnd(IEachPairUntilEnd<Integer,IWrapper> f);
    public List<String> toList();
}
