import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

public class MCCR {
    public static int MCCR(EdgeWeightedGraph G) {
        // TODO
        int cost = 0;
        HashSet<Integer> visitedV = new HashSet<>();
        HashSet<Edge> visitedE = new HashSet<>();
        PriorityQueue<Edge> potentialEdges = new PriorityQueue<>();
        Integer vertex = 1;
        while (visitedV.size() < G.numberOfV() - 1) {
            for (Edge e : G.edges(vertex)) {
                if (!visitedE.contains(e))
                    potentialEdges.add(e);
                visitedE.add(e);
            }
            visitedV.add(vertex);

            Edge minEdge = potentialEdges.poll();
            Integer v = minEdge.either();
            Integer w = minEdge.other(v);
            while (visitedV.contains(v) && visitedV.contains(w)) {
                minEdge = potentialEdges.poll();
                v = minEdge.either();
                w = minEdge.other(v);
            }

            // Update vertex
            vertex = visitedV.contains(v) ? w : v;
            cost += minEdge.weight();
        }
        return cost;
    }

}

