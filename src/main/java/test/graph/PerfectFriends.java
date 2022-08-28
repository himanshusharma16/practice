package test.graph;

import java.io.*;
import java.util.*;

public class PerfectFriends {
    public static class Edge {
        int origin;
        int end;

        public Edge(int o, int e) {
            origin = o;
            end = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // write your code here
        ArrayList<Edge>[] graph = new ArrayList[n];
        int m = n;
        while(--m>=0){
            graph[m] = new ArrayList<Edge>();
        }
        for (int i = 0; i < k; i++) {
            ArrayList<Edge> list = new ArrayList<>();
            String[] arr = br.readLine().split(" ");
            int v1 = Integer.parseInt(arr[0]);
            int v2 = Integer.parseInt(arr[1]);
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        List<List<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                List<Integer> comp = new ArrayList<>();
                drawComponents(comp,i,visited,graph);
                comps.add(comp);
            }
        }

        int total = 0;
        for(int i=0; i<comps.size(); i++){
            List<Integer> outer = comps.get(i);
            for(int j=i+1;j<comps.size();j++){
                List<Integer> inner = comps.get(j);
                total += outer.size()*inner.size();
            }
        }
        System.out.println(total);

    }

    private static void drawComponents(List<Integer> comp, int i, boolean[] visited,ArrayList<Edge>[] graph){
        visited[i] = true;
        comp.add(i);
        for(Edge e : graph[i]){
            int des = e.end;
            if(!visited[des]){
                drawComponents(comp,des,visited,graph);
            }
        }
    }
}
