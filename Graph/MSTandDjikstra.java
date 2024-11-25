/*Q1. Commutable Islands (Disjoint Set or Union Mathod) */

public class Solution {
  public int solve(int A, ArrayList<ArrayList<Integer>> B) {
    B.sort(Comparator.comparingInt(o -> o.get(2)));
    int[] parent = new int[A];
    Arrays.fill(parent, -1);
    int ans = 0;
    for (ArrayList<Integer> bridge : B) {
      int parentOne = findParent(parent, bridge.get(0) - 1);
      int parentTwo = findParent(parent, bridge.get(1) - 1);
      if (parentOne != parentTwo) {
        union(parent, parentOne, parentTwo);
        ans += bridge.get(2);
      }
    }
    return ans;
  }

  private void union(int[] parent, int x, int y) {
    int parentOne = findParent(parent, x);
    int parentTwo = findParent(parent, y);
    parent[parentOne] = parentTwo;
  }

  private int findParent(int[] parent, int i) {
    if (parent[i] == -1) {
      return i;
    }
    return findParent(parent, parent[i]);
  }
}

/*Q2. Dijkstra
Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.
You have to find an integer array D of size A such that:

D[i]: Shortest distance from the C node to node i.
If node i is not reachable from C then -1.
Note:

There are no self-loops in the graph.
There are no multiple edges between two pairs of vertices.
The graph may or may not be connected.
Nodes are numbered from 0 to A-1.
Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.*/

Solution :

public class Solution {
    static int maxn = 100009;
    static int[] vis = new int[maxn];
    static ArrayList < ArrayList < Pair > > adj;
    public static void graph() {
        adj = new ArrayList < ArrayList < Pair > > (maxn);
        for (int i = 0; i < maxn; i++) {
            vis[i] = 0;
            adj.add(new ArrayList < Pair > ());
        }
    }
    public int[] solve(int A, int[][] B, int C) {
        graph();
        for (int[] edge: B) {
            adj.get(edge[0]).add(new Pair(edge[2], edge[1]));
            adj.get(edge[1]).add(new Pair(edge[2], edge[0]));
        }
        return dijkstra(A, C);
    }

    public static int[] dijkstra(int n, int source) {
        PriorityQueue < Pair > pq = new PriorityQueue < Pair > (new CustomComp());
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;
        pq.offer(new Pair(0, source));

        while (pq.size() != 0) {
            Pair temp = pq.poll();
            int u = temp.b;
            if (vis[u] == 1)
                continue;
            vis[u] = 1;
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i).b, w = adj.get(u).get(i).a;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Pair(dist[v], v));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
    }
}
class Pair {
    int a, b;
    public Pair(int u, int v) {
        this.a = u;
        this.b = v;
    }
}
class CustomComp implements Comparator < Pair > {
    @Override
    public int compare(Pair aa, Pair bb) {
        return aa.a - bb.a;
    }
}

/*Q3. Construction Cost
Flipkart has ‘A’ local distribution centers located across a large metropolitan city. 
Each distribution center needs to be interconnected through roads to facilitate efficient movement of goods. 
The cost of constructing a road between any two distribution centers is represented by the weight of the edge connecting them.

Given a graph with ‘A’ nodes representing the distribution centers and C weighted edges representing the possible roads between them,
your task is to find the minimum total cost of constructing roads such that every distribution center can be reached from the first distribution center.

Cost Calculation:
The cost of constructing the roads is the sum of the weights of the edges selected for the construction.

NOTE: Return the answer modulo 10^9+7 as the answer can be large.

Solution :*/
public class Solution {
    static int maxn = 100009;
    static int[] arr = new int[maxn];
    static int[] sz = new int[maxn];
    static ArrayList < pair > edges;
    static long mod = 1000000007;
    public int solve(int A, int[][] B) {
        ini();
        for (int i = 0; i < B.length; i++)
            edges.add(new pair(B[i][2], i));
        Collections.sort(edges);
        int ans = 0;
        for (pair p: edges) {
            int ind = p.second;
            int val = p.first;
            int u = B[ind][0];
            int v = B[ind][1];
            if (root(u) == root(v))
                continue;
            else {
                un(u, v);
                ans += (int) val;
                ans %= mod;
            }
        }
        return (int) ans;

    }
    public static void ini() {
        edges = new ArrayList < pair > ();
        for (int i = 0; i < maxn; i++) {
            arr[i] = i;
            sz[i] = 1;
        }
    }
    public static int root(int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return a;
    }
    public static void un(int a, int b) {
        int ra = root(a);
        int rb = root(b);
        if (sz[ra] <= sz[rb]) {
            arr[ra] = rb;
            sz[rb] += sz[ra];
        } else {
            arr[rb] = ra;
            sz[ra] += sz[rb];
        }
    }
}
class pair implements Comparable < pair > {
    int first;
    int second;
    public pair(int a, int b) {
        this.first = a;
        this.second = b;
    }
    @Override
    public int compareTo(pair temp) {
        return this.first - temp.first;
    }
}

/*
--Edges in MST
Given a undirected weighted graph with A nodes labelled from 1 to A with M edges given in a form of 2D-matrix B of size M * 3 where B[i][0] and B[i][1] denotes the two nodes connected by an edge of weight B[i][2].

For each edge check whether it belongs to any of the possible minimum spanning tree or not , return 1 if it belongs else return 0.

Return an one-dimensional binary array of size M denoting answer for each edge.

NOTE:

The graph may be disconnected in that case consider mst for each component.
No self-loops and no multiple edges present.
Answers in output array must be in order with the input array B output[i] must denote the answer of edge B[i][0] to B[i][1].
*/

Solution :
public class Solution {
    static int maxn = 300009;
    static int[] arr = new int[maxn];
    static int[] sz = new int[maxn];
    static ArrayList < pair > edges;
    public int[] solve(int A, int[][] B) {
        ini();
        for (int i = 0; i < B.length; i++)
            edges.add(new pair(B[i][2], i, B[i][0], B[i][1]));
        int[] ans = new int[B.length];
        Collections.sort(edges);
        int i = 0;
        int m = B.length;
        while (i < m) {
            int j = i;
            while (j < m && edges.get(j).a == edges.get(i).a) {
                if (root(edges.get(j).c) != root(edges.get(j).d))
                    ans[edges.get(j).b] = 1;
                j++;
            }
            j = i;
            while (j < m && edges.get(j).a == edges.get(i).a) {
                if (root(edges.get(j).c) != root(edges.get(j).d))
                    un(edges.get(j).c, edges.get(j).d);
                j++;
            }
            i = j;
        }
        return ans;
    }
    public static void ini() {
        edges = new ArrayList < pair > ();
        for (int i = 0; i < maxn; i++) {
            arr[i] = i;
            sz[i] = 1;
        }
    }
    public static int root(int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return a;
    }
    public static void un(int a, int b) {
        int ra = root(a);
        int rb = root(b);
        if (sz[ra] <= sz[rb]) {
            arr[ra] = rb;
            sz[rb] += sz[ra];
        } else {
            arr[rb] = ra;
            sz[ra] += sz[rb];
        }
    }
}
class pair implements Comparable < pair > {
    int a, b, c, d;
    pair(int e, int f, int g, int h) {
        this.a = e;
        this.b = f;
        this.c = g;
        this.d = h;
    }
    @Override
    public int compareTo(pair aa) {
        return this.a - aa.a;
    }
}