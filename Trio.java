public class Trio implements Comparable<Trio> {
    int i;
    int j;
    Integer cost;

    public Trio(int i, int j, int cost){
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    @Override
    public int compareTo(Trio o) {
        //return cost.compareTo(o.cost);
        return o.cost.compareTo(cost);
    }
}
