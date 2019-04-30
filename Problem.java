package src;

import java.util.*;

public class Problem {
    public static final int MAX_NODES = 5000;
    public static final int MAX_TASK = 50000;
    public static final int MAX_DURATION = 100;


    List<Pair>[] inAdjacentNodes;
    List<Pair>[] outAdjacentNodes;

    public Problem(int vertices, int[][] edges) {
        buildGraph(vertices, edges);
    }

    @SuppressWarnings("unchecked")
    private void buildGraph(int vertices,int[][] edges) {
        inAdjacentNodes = new List[vertices];
        outAdjacentNodes = new List[vertices];

        for(int i = 0 ; i < vertices; i++) {
            outAdjacentNodes[i] = new LinkedList<>();
            inAdjacentNodes[i] = new LinkedList<>();
        }

        for(int[] edge : edges) {
            outAdjacentNodes[edge[0]].add(new Pair(edge[1],edge[2]));
            inAdjacentNodes[edge[1]].add(new Pair(edge[0],edge[2]));
        }
    }

    public Set<TaskInfo> solve_() {
        Queue<Integer> ready = new LinkedList<>();
        int[] costToVertex = new int[inAdjacentNodes.length];
        int[] inDegree = new int[inAdjacentNodes.length];
        ready.add(1);
        SortedSet<TaskInfo> result = new TreeSet<>();

        for(int i = 0; i < inDegree.length; i++){
            inDegree[i] = this.inAdjacentNodes[i].size();
        }

        while(!ready.isEmpty()){
            int vertex =  ready.remove();
            for(Pair v : outAdjacentNodes[vertex]){
                int cost = costToVertex[vertex] + v.cost;

                inDegree[v.vertice]--;
                if(inDegree[v.vertice] == 0)
                    ready.add(v.vertice);

                if(cost > costToVertex[v.vertice]){
                    costToVertex[v.vertice] = cost;
                }
            }
        }

        ready.add(1);

        boolean[] processed = new boolean[inAdjacentNodes.length];
        processed[1] = true;

        while(!ready.isEmpty()){
            int vertex =  ready.remove();

            for(Pair v : outAdjacentNodes[vertex]){
                int cost = costToVertex[vertex] + v.cost;

                if(!processed[v.vertice]) {
                    ready.add(v.vertice);
                    processed[v.vertice] = true;
                }

                if(cost < costToVertex[v.vertice] && inAdjacentNodes[v.vertice].size() > 1){
                    result.add(new TaskInfo(vertex,v.vertice));
                }
                if(cost > costToVertex[v.vertice]){
                    costToVertex[v.vertice] = cost;
                }

            }
        }

        return result;
    }


    public Set<TaskInfo> solve() {
    	final Deque<Integer> ready = new LinkedList<Integer>();
    	final Set<TaskInfo> result = new TreeSet<TaskInfo>();
    	final int[] distance = new int[this.inAdjacentNodes.length];
    	final int[] inDegree = new int[this.inAdjacentNodes.length];
    	
    	for (int i = 1; i < this.inAdjacentNodes.length; i++)
    		inDegree[i] = this.inAdjacentNodes[i].size();
    	
    	ready.push(1);
    	
    	do {
    		final int tail = ready.pop();
    		if (this.inAdjacentNodes[tail].size() > 1) {
    			List<TaskInfo> biggestFound = new LinkedList<TaskInfo>(); 
    			for (Pair previous : this.inAdjacentNodes[tail]) {
    				final TaskInfo ti = new TaskInfo(previous.vertice, tail);
    				final int currCost =  distance[previous.vertice] + previous.cost; 
    				if (currCost >= distance[tail]) {
    					if (currCost > distance[tail]) {
	    					distance[tail] = currCost;
	    					result.addAll(biggestFound);
	    					biggestFound = new LinkedList<TaskInfo>();
    					}
    					biggestFound.add(ti);
    				}
    				else {
    					result.add(ti);
    				}
    			}
    		}
    		
    		for (Pair headPair : this.outAdjacentNodes[tail]) {
    			final int head = headPair.vertice;
    			inDegree[head]--;
    			if (inDegree[head] == 0)
    				ready.add(head);
    		}
    		
    	} while (!ready.isEmpty());

        return result;
    }
}
