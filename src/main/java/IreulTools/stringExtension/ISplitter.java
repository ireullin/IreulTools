package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import IreulTools.functionalProgramming.IEachPair;

import java.util.List;

/**
 * Created by ireullin on 2016/10/22.
 */
public interface ISplitter {
    public ISplitter putDelim(char[] chars);
    public ISplitter putDelim(List<Character> chars);
    public ISplitter putDelim(char c);
    public void each(IEachPair<IWrapper,ISplitterInfo> pair);
    public List<IWrapper> toList();
}
