package src;

public class Task implements Comparable<Task> {
    int i;
    int j;
    Integer cost;

    @Override
    public int compareTo(Task o) {
        return cost.compareTo(o.cost);
    }
}
