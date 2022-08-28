package test.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Infections {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        int src = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[vtces];
        if(t == 1)
            System.out.println(1);
        else
            System.out.println(getInfections(src,visited,graph,t));
    }

    public static int getInfections(int src, boolean[] visited, ArrayList<Edge>[] graph, int time){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        q.add(null);
        int count = 0;
        time--;
        while(!q.isEmpty() && time >= 0){
            Integer v = q.poll();
            if(v == null){
                time--;
                q.add(null);
                continue;
            } else if( visited[v] == false) {
                count++;
                visited[v] = true;
                for (Edge e : graph[v]) {
                    if (!visited[e.nbr]) {
                        q.add(e.nbr);
                    }
                }
            }
        }
        return count;
    }

}
