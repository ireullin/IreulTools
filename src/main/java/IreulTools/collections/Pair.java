package IreulTools.collections;

public class Pair<K,V>{
    final private K first;
    final private V second;
    public static<K,V> Pair<K,V> of(K first, V second){
        return new Pair<>(first, second);
    }
    private Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    public K first(){return first;}
    public V second(){return second;}

    @Override
    public String toString() {
        return String.format("[\"%s\",\"%s\"]", first, second);
    }
}
