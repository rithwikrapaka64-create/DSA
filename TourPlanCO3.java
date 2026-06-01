import java.util.*;

class Graph {
    int V;
    LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    Graph(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V]; // type-safe cast
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u); // undirected graph
    }

    void BFS(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        System.out.print("BFS Traversal: ");
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int neigh : adj[node]) {
                if (!visited[neigh]) {
                    visited[neigh] = true;
                    q.add(neigh);
                }
            }
        }
        System.out.println();
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int neigh : adj[v]) {
            if (!visited[neigh]) DFSUtil(neigh, visited);
        }
    }

    void DFS(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Traversal: ");
        DFSUtil(start, visited);
        System.out.println();
    }

    void dijkstra(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            for (int v : adj[u]) {
                if (dist[u] + 1 < dist[v]) { // weight = 1 for simplicity
                    dist[v] = dist[u] + 1;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To " + i + " = " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

public class TourPlanCO3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of destinations (vertices): ");
        int V = sc.nextInt();
        Graph g = new Graph(V);

        System.out.print("Enter number of routes (edges): ");
        int E = sc.nextInt();
        System.out.println("Enter each route as: u v");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        System.out.print("Enter start destination for BFS/DFS: ");
        int start = sc.nextInt();
        g.BFS(start);
        g.DFS(start);

        System.out.print("Enter source for shortest path (Dijkstra): ");
        int src = sc.nextInt();
        g.dijkstra(src);

        sc.close();
    }
}
