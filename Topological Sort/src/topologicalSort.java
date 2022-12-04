import java.util.ArrayList;


public class topologicalSort {

    Digraph g; // the graph
    int indegree[];

    public topologicalSort(Digraph graph) {
        this.g = graph;
        indegree = new int[g.V()]; // keeps track of the indegrees. initialize them as 0.


    }

    public ArrayList<Integer> topological() {
        for (int i = 0; i < g.V(); i++) {
            ArrayList<Integer> temp
                    = g.adj(i);
            for (int node : temp) {
                indegree[node]++;
            }
        }

        Queue<Integer> q
                = new Queue<Integer>();
        for (int i = 0; i < g.V(); i++) {
            if (indegree[i] == 0)
                q.enqueue(i);
        }
        int cnt = 0;


        ArrayList<Integer> topOrder = new ArrayList<>(); // Create a Arraylist to store result
        while (!q.isEmpty()) {  // Extract front of queue  (or perform dequeue) and add it to topological order


            int u = q.dequeue();
            topOrder.add(u);



            for (int node : g.adj(u)) {  // Iterate through all its neighbouring nodes  of dequeued node u and decrease their in-degree by 1


                if (--indegree[node] == 0) // If in-degree becomes zero, add it to queue
                    q.enqueue(node);
            }
            cnt++;
        }
        if (cnt != g.V()) {
            System.out.println(
                    "There exists a cycle in the graph");
            return null;
        }



        return topOrder; // return topological order
    }


}
