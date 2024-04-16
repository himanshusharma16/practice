package test.graph;

import java.io.*;
import java.util.*;

public class Hamiltonian {

    static class Edge {

        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
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
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());

        // write all your codes here
        boolean[] visited = new boolean[vtces];
        String psf = src + "";
        printHamiltonians(graph, src, visited, psf, vtces);

    }

    public static void printHamiltonians(ArrayList<Edge>[] graph, int src, boolean[] visited, String psf, int n) {
        if (psf.length() == n) {
            printPath(psf, graph, n);
            return;
        }
        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (!visited[edge.nbr]) {
                printHamiltonians(graph, edge.nbr, visited, psf + edge.nbr, n);
            }
        }
        visited[src] = false;
    }

    private static void printPath(String path, ArrayList<Edge>[] graph, int n) {
        int src = Integer.parseInt(path.substring(0, 1));
        int end = Integer.parseInt(path.substring(n - 2, n - 1));
        boolean isCycle = false;
        for (Edge edge : graph[src]) {
            if (edge.nbr == end) {
                isCycle = true;
                break;
            }
        }
        if (isCycle) {
            System.out.println(path + "*");
        } else {
            System.out.println(path + ".");
        }
    }


}