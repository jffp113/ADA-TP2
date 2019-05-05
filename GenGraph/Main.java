package GenGraph;

import java.io.IOException;
import java.util.*;

public class Main {
    public static final int min = 1;
    public static final int max = 5000;

    public static void main(String[] args) throws IOException {


            Random r = new Random();
            Set<TaskInfo> task = new HashSet<>();
            int last = r.nextInt((max - min) + 1) + min;
            task.add(new TaskInfo(1,last));
            int numbEdges = 50000;
            for(int i = 0 ; i < numbEdges; i++) {
                int usingLast = r.nextInt((50 - 3) + 1) + 3;
                int insideLast = 0;
                for(int l = 0 ; l < usingLast; l++) {
                    boolean succ = false;
                    int x = last;
                    insideLast = r.nextInt((max - min) + 1) + min;
                    int y = insideLast;
                    if (x != y) {
                        TaskInfo t = new TaskInfo(x, y);

                        if (isCompatible(task, t))
                            succ = task.add(t);
                    }

                    if (!succ) {
                        i--;
                        l--;
                    }else{
                        i++;
                    }
                    if(i >= numbEdges )
                        break;

                }
                last = insideLast;
            }

            connectAllToEnd(task);
            System.out.println(max + " " + task.size());
            for(TaskInfo t : task){
                int time = r.nextInt((100 - 1) + 1) + 1;
                String[] x = t.toString().split( " ");
                System.out.printf("%s %s %d\n",x[0],x[1],time);
            }
        }


    private static boolean isCompatible(Set<TaskInfo> tasks, TaskInfo task) {
        List<Integer>[] inAdjacentNodes = new List[max + 1];
        List<Integer>[] outAdjacentNodes = new List[max + 1];
        buildGraph(inAdjacentNodes,outAdjacentNodes,tasks,task);

        return isAcycle(inAdjacentNodes,outAdjacentNodes);
    }


    private static void buildGraph(List<Integer>[] inAdjacentNodes,List<Integer>[] outAdjacentNodes,Set<TaskInfo> tasks, TaskInfo task) {

        for(int i = 0 ; i < max + 1; i++) {
            outAdjacentNodes[i] = new LinkedList<>();
            inAdjacentNodes[i] = new LinkedList<>();
        }

        for(TaskInfo e : tasks) {
            outAdjacentNodes[e.inicialValue].add(e.finalValue);
            inAdjacentNodes[e.finalValue].add(e.inicialValue);
        }

        if(task != null) {
            outAdjacentNodes[task.inicialValue].add(task.finalValue);
            inAdjacentNodes[task.finalValue].add(task.inicialValue);
        }
    }

    private  static boolean isAcycle(List<Integer>[] inAdjacentNodes,List<Integer>[] outAdjacentNodes){
        int numProcNodes = 0;
        List<Integer> ready = new LinkedList<>();
        int[] inDegree = new int[inAdjacentNodes.length];

        int numInicialNodes = 0;
        for (int i = 1; i < inAdjacentNodes.length; i++) {
            inDegree[i] = inAdjacentNodes[i].size();
            if(inDegree[i] == 0) {
                numInicialNodes++;
                ready.add(i);
            }
        }

        while(!ready.isEmpty()){
            Integer node = ready.remove(0);
            numProcNodes++;

            for(Integer n : outAdjacentNodes[node]){
                inDegree[n]--;
                if(inDegree[n] == 0) {
                    ready.add(n);
                }
            }
        }


        return inAdjacentNodes.length == numProcNodes + 1;
    }

    private static void connectAllToEnd(Set<TaskInfo> tasks) {
        List<Integer>[] inAdjacentNodes = new List[max + 1];
        List<Integer>[] outAdjacentNodes = new List[max + 1];
        buildGraph(inAdjacentNodes, outAdjacentNodes, tasks, null);


        for (int i = 0; i < inAdjacentNodes.length; i++) {
            if (outAdjacentNodes[i].size() == 0) {
                tasks.add(new TaskInfo(i,max));
            }
        }
    }
}
