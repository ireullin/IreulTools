package IreulTools.collections;

public class ImmutableWrapper extends Wrapper{

    @Override
    public IWrapper reset(Object value) throws Exception {
        throw new ImmutableWrapperException("This is immutable");
    }

    private ImmutableWrapper(Object obj){
        super(obj);
    }

    public static IWrapper of(Object obj){
        return new ImmutableWrapper(obj);
    }

    public static IWrapper ofNull(){
        return new ImmutableWrapper(null);
    }

}
