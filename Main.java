
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        int[][] edges;
        Problem problem;
        String[] splited;

        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        splited = input.readLine().split(" ");

        int n = Integer.parseInt(splited[0]);
        int k = Integer.parseInt(splited[1]);

        edges = new int[k][3];
        for(int i = 0 ; i < k ; i++) {
            splited = input.readLine().split(" ");
            edges[i][0] = Integer.parseInt(splited[0]);
            edges[i][1] = Integer.parseInt(splited[1]);
            edges[i][2] = Integer.parseInt(splited[2]);
        }


        Problem p = new Problem(n + 1,edges);
        Set<TaskInfo> it = p.solve();

        System.out.println(it.size());
        for(TaskInfo i : it)
            System.out.println(i);
        }


}
