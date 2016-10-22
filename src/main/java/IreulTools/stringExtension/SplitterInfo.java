package IreulTools.stringExtension;

/**
 * Created by ireullin on 2016/10/22.
 */
public class SplitterInfo implements ISplitterInfo {

    private int startAt = 0;
    private int endAt = 0;
    private int index = 0;

    public SplitterInfo(int startAt, int endAt, int index){
        this.startAt = startAt;
        this.endAt = endAt;
        this.index = index;
    }

    @Override
    public int startAt() {
        return startAt;
    }

    @Override
    public int endAt() {
        return endAt;
    }

    @Override
    public int index() {
        return index;
    }
}
