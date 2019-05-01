
public class TaskInfo implements Comparable<TaskInfo>{
    public final Integer inicialValue;
    public final Integer finalValue;

    public TaskInfo(int inicialValue, int finalValue) {
        this.inicialValue = inicialValue;
        this.finalValue = finalValue;
    }

    @Override
    public int compareTo(TaskInfo o) {
        int v =  inicialValue.compareTo(o.inicialValue);

        if(v != 0)
            return v;

        return finalValue.compareTo(o.finalValue);
    }

    public String toString() {
        return inicialValue + " " + finalValue;
    }

}
