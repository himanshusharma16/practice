package main.java.practice.map;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,5},{2,3,8},{1,5,10}};
        new Solution().findAllPeople(6,arr,1);
    }

    class Pair implements Comparable<Pair>{
        int dest;
        int time;

        public Pair(int o, int t){
            this.dest = o;
            this.time = t;
        }

        public int compareTo(Pair p){
            return this.time - p.time;
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Map<Integer,PriorityQueue<Pair>> map = new HashMap<>();

        for(var m : meetings){
            pq.offer(m[2]);

            var q = map.getOrDefault(m[0],new PriorityQueue<Pair>());
            q.add(new Pair(m[1],m[2]));
            map.put(m[0],q);

            q = map.getOrDefault(m[1],new PriorityQueue<Pair>());
            q.add(new Pair(m[0],m[2]));
            map.put(m[1],q);
        }

        Set<Integer> ans = new HashSet<>();
        ans.add(0);
        ans.add(firstPerson);

        while(!pq.isEmpty()){
            Set<Integer> newSet = new HashSet<>();
            var time = pq.poll();
            for(int s : ans){
                newSet.add(s);
                if(map.get(s) != null){
                    var q = map.get(s);
                    while(!q.isEmpty() && q.peek().time == time){
                        newSet.add(q.poll().dest);
                    }
                    if(q.isEmpty())
                        map.remove(s);
                }
            }
            ans = newSet;
        }
        return new ArrayList<>(ans);
    }
}