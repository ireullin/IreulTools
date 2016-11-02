package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import IreulTools.functionalProgramming.IEachPair;

import java.util.List;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface ISplit {
    public ISplit with(String s);
    public void each(IEachPair<Integer,IWrapper> each);
    public List<String> toList();
}
