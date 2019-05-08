
public class EdgeEndpoints implements Comparable<EdgeEndpoints>{
    private final Integer tail;
    private final Integer head;

    public EdgeEndpoints(final int tail, final int head) {
        this.tail = tail;
        this.head = head;
    }

    @Override
    public int compareTo(EdgeEndpoints o) {
        int v =  this.tail.compareTo(o.tail);

        if(v != 0)
            return v;

        return this.head.compareTo(o.head);
    }

    public String toString() {
        return this.tail + " " + this.head;
    }

}
