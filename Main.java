

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {
	
	private static final String REGEX_BREAK = " ";

    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

	        final String[] firstLineVals = input.readLine().split(REGEX_BREAK);
	        final int numVertices = Integer.parseInt(firstLineVals[0]);
	        final int numEdges = Integer.parseInt(firstLineVals[1]);
	
	        final UnderControlSolver underControl = new UnderControlSolver(numVertices);
	        for(int edgesRead = 0 ; edgesRead < numEdges ; edgesRead++) {
	        	final String lineRead = input.readLine();
	        	final String[] lineParams = lineRead.split(REGEX_BREAK);
	        	final int tail = Integer.parseInt(lineParams[0]);
	        	final int head = Integer.parseInt(lineParams[1]);
	        	final int cost = Integer.parseInt(lineParams[2]);
	        	underControl.addEdge(tail, head, cost);
	        }
	        
	        underControl.solve();
	        System.out.println(underControl.getNumDelayedEdges());
	        final Iterator<EdgeEndpoints> solution = underControl.getDelayedEdges();
	        while (solution.hasNext())
	        	System.out.println(solution.next());
        }
    }
}