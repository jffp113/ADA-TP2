import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UnderControlSolver {
    
    /**
     * Represents the state of a node with no unprocessed predecessors
     */
    private static final int NO_PREDECESSORS = 0;
    
    /**
     * Number representing the first node in the graph.
     * The first node is the only node with no predecessors.
     */
    private static final int FIRST_NODE = 1;
    
    /**
     * Contains the number of predecessors of a node.
     */
    private final int[] inDegree;
    
    /**
     * Contains, for every node, the heads of the edges that have this node as tail.
     */
    private final List<OutgoingEdge>[] outAdjacentNodes;
    
    /**
     * Contains the edges that can be delayed in the correct order to be iterated.
     */
    private List<EdgeEndpoints> delayedEdges = null;
    
    @SuppressWarnings("unchecked")
	public UnderControlSolver (int vertices) {
    	this.inDegree = new int[vertices + 1];
        outAdjacentNodes = new List[vertices + 1];
        
        for(int i = FIRST_NODE ; i <= vertices; i++) 
            outAdjacentNodes[i] = new LinkedList<>();
        
    }
    
    /**
     * Adds an edge to the graph data structures of this instance
     * @param tail The node that is tail of this edge
     * @param head The node that is head of this edge
     * @param cost The cost of this edge
     */
    public void addEdge (final int tail, final int head, final int cost) {
    	this.inDegree[head]++;
    	this.outAdjacentNodes[tail].add(new OutgoingEdge(head, cost));
    }
    
    /**
     * Finds the edges that can be delayed and stores the value in the assigned data structure.
     */
    public void solve() {
    	final int[] maxDistanceNode = this.biggestPathToNodes();
    	this.delayedEdges = this.findDelayableNodes(maxDistanceNode);
    }
    
    /**
     * Finds the cost of the longest path to each node.
     * @return An array containing the biggest cost per node.
     */
    private int[] biggestPathToNodes () {
    	
    	/*
    	 * Contains the nodes that have no unprocessed predecessors and haven't been processed.
    	 */
        final Deque<Integer> ready = new LinkedList<>();
        
        final int[] costToVertex = new int[this.outAdjacentNodes.length];

        ready.add(FIRST_NODE);
        while(!ready.isEmpty()){
            final int tail =  ready.remove();
            for(OutgoingEdge outgoing : outAdjacentNodes[tail]){
            	final int head = outgoing.head;
                final int pathDistance = costToVertex[tail] + outgoing.cost;

                /*
                 * As a node is processed, the adjacent will have one unprocessed predecessor fewer.
                 */
                if(--inDegree[head] == NO_PREDECESSORS)
                    ready.add(head);

                if(pathDistance > costToVertex[head])
                    costToVertex[head] = pathDistance;
            }
        }
    	return costToVertex;
    }
    
    /**
     * Finds the cost of the longest path to each node.
     * @param maxDistanceNode The costs of the longest path to each node.
     * @return
     */
    private List<EdgeEndpoints> findDelayableNodes(int[] maxDistanceNode) {
    	final List<EdgeEndpoints> unsortedResult = new LinkedList<>();
    	
    	for (int tail = FIRST_NODE; tail < this.outAdjacentNodes.length; tail++) {
            for(OutgoingEdge outgoing : outAdjacentNodes[tail]) {
                final int pathCost = maxDistanceNode[tail] + outgoing.cost;
                final int head = outgoing.head;

                if(pathCost < maxDistanceNode[head])
                	unsortedResult.add(new EdgeEndpoints(tail, head));

            }
        }
    	
    	/*
    	 * Switches data structure of the results.
    	 * Sorting an array list is faster than a linked list.
    	 */
    	final List<EdgeEndpoints> sortedResults = new ArrayList<>(unsortedResult);
    	Collections.sort(sortedResults);
		return sortedResults;
    }
    
    /**
     * @return The number of delayed edges.
     */
    public int getNumDelayedEdges () {
    	return this.delayedEdges.size();
    }
    
    /**
     * @return An iterator to list the edges that can be delayed
     */
    public Iterator<EdgeEndpoints> getDelayedEdges () {
    	return this.delayedEdges.iterator();
    }

}
