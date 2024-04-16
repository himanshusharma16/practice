package main.java.practice.map;

import java.util.*;
public class TestMap {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> fmap = new HashMap<>();
        List<Character> ll = new ArrayList<>();
        for (char c : tasks) {
            int freq = fmap.getOrDefault(c, 0);
            if (freq == 0)
                ll.add(c);
            fmap.put(c, freq + 1);
        }
        int llindex = 0;
        Set<Character> set = new HashSet<>();
        char[] window = new char[n + 1];
        for (int i = 0; i < n + 1; i++) {
            if (llindex >= ll.size())
                llindex = 0;
            char c = ll.get(llindex);
            if (fmap.get(c) != null) {
                window[i] = c;
                fmap.put(c, fmap.get(c) - 1);
            }
        }
        return llindex;
    }
}
