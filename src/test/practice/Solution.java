package test.practice;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        List<Integer> result = new ArrayList<Integer>();
        int i = 0;
        while(i<=(slen-plen)){
            if(isAnagram(s.substring(i,i+plen),p))
                result.add(i);
        }
        return result;
    }

    public boolean isAnagram(String one, String two){
        char[] oneArr = one.toCharArray();
        for(char c:oneArr){
            two = two.replaceFirst(Character.toString(c),"0");
        }
        try
        {if(Integer.parseInt(two)==0)
            return true;
        } catch (Exception e){
            return false;
        }
        return false;
    }

    public int trap(int[] height) {
        int maxvol = 0;
        int currmax = 0,currvol = 0;
        for(int i = 0;i<height.length;i++){
            if(currmax <= height[i]){
                currmax = height[i];
                currvol += (currmax-height[i]);
                maxvol += currvol;
                currvol = 0;
            } else {
                currvol += (currmax-height[i]);
            }
        }
        if(currvol != 0){
            int maxfromlast = 0;
            currvol = 0;
            for(int i = height.length-1;height[i]!=currmax;i--){
                if(maxfromlast <= height[i]){
                    maxfromlast = height[i];
                    currvol += (maxfromlast-height[i]);
                } else
                    currvol += (maxfromlast-height[i]);
            }
            maxvol+= currvol;
        }
        return maxvol;
    }

    public boolean isAnagram2(String one, String two){
        char[] oneArr = one.toCharArray();
        char[] twoArr = two.toCharArray();
        Arrays.sort(oneArr);
        Arrays.sort(twoArr);
        int i=0;
        for(char c:oneArr){
            if(twoArr[i]!=c)
                return false;
            else
                i++;
        }
        return true;
    }

    public int maxSum(int[] arr){
        int maxsofar = arr[0];
        int currmax = arr[0];
        for(int i = 0;i<arr.length;i++){
            int curr = arr[i];
            currmax = Math.max(curr, curr + currmax);
            maxsofar = Math.max(maxsofar, currmax);
        }
        return maxsofar;
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    int rowmax = 0;
    int colmax = 0;

    public int numIslands(char[][] grid) {
        int count = 0;
        rowmax = grid.length;
        colmax = grid[0].length;
        Set<String> set = new HashSet<>();
        for(int i =0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(isIsland(grid,set,i,j))
                    count++;
            }
        }
        return count;
    }

    public boolean isIsland(char[][] c,Set<String> set,int i,int j){
        if(i==rowmax||j==colmax||i<0||j<0)
            return false;
        String ij = Integer.toString(i)+Integer.toString(j);
        if(set.contains(ij))
            return false;

        if(c[i][j] == '1'){
            set.add(ij);
            isIsland(c,set,i,j+1);
            isIsland(c,set,i+1,j);
            isIsland(c,set,i,j-1);
            return true;
        }
        return false;
    }

    public String addBinary(String a, String b) {
        int alen = a.length()-1,blen=b.length()-1,carry =0;
        List<Integer> ll = new ArrayList<>();
        StringBuilder sb= new StringBuilder();
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        while(alen>=0 && blen>=0){
            int temp = Character.getNumericValue(ca[alen])+Character.getNumericValue(cb[blen])+carry;
            if(temp>1){
                carry = 1;
                temp = temp%2;
            } else
                carry = 0;
            ll.add(temp);
            alen--;
            blen--;
        }
        while(alen>=0){
            int temp = Character.getNumericValue(ca[alen])+carry;
            if(temp>1){
                carry = 1;
                temp = temp%2;
            } else
                carry = 0;
            ll.add(temp);
            alen--;
        }
        while(blen>=0) {
            int temp = Character.getNumericValue(cb[blen])+carry;
            if(temp>1){
                carry = 1;
                temp = temp%2;
            } else
                carry = 0;
            ll.add(temp);
            blen--;
        }
        if(carry==1)
            ll.add(1);
        for(int i = ll.size()-1;i>=0;i--){
            sb = sb.append(Integer.toString(ll.get(i)));
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        String[] sarr = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i=sarr.length-1;i>=0;i--){
            if(sarr[i]!="" && sarr[i] != " ")
                sb.append(sarr[i].trim()).append(" ");
        }
        return sb.toString().trim();
    }

    public void test(){
        HashMap<String,String> hmap = new HashMap<>();
        hmap.put("A","1");
        hmap.put("D","2");
        hmap.put("E","5");
        hmap.put("B","4");
        hmap.put("G","0");
        hmap.put("W","7");
        hmap.put("Z","6");
        hmap.keySet().stream().sorted().forEach(c->System.out.println(c+","+hmap.get(c)));
        LinkedHashMap<String,String> sortedmap = hmap.entrySet().stream().sorted((a,b)->{return a.getValue().compareTo(b.getValue());}).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));

       // HashMap<String,String> mapp = hmap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        System.out.println("DOne");

    }

    public boolean isMatch(String s, String p) {
        boolean isMatch = true;
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int i = 0, j = 0;
        if (p.length() == 1)
            return s.equals(p);
        while (isMatch && i <= p.length() - 1 && j <= s.length() - 1) {
            char c = sArr[j];
            char pc = pArr[i];
            char pnext = '1';
            if(i != p.length()-1)
                pnext = pArr[i + 1];
            if (pnext != '*') {
                isMatch = (c == pc || pc == '.');
                j++;
            } else {
                i++;
                if (pc == '.')
                    return true;
                else {
                    //isMatch = (c == pc);
                    while (j <= s.length() - 1 && sArr[j] == pc)
                        j++;
                }
            }
            i++;
        }
        if(isMatch && j < s.length()){
            isMatch = false;
        }
        while (isMatch && i != p.length()){
            char pn = pArr[i];
            if( pn == '*' || ( i!=p.length()-1 && pArr[i+1] == '*'))
                i++;
            else
                isMatch = false;
        }
        return isMatch;
    }

    // catalan number Cn+1 = 2(2n+1)/(n+2) * Cn
    // number of permutations with nX and nY is Cn. so count X is always greater than Y. also can be used to get complete paranthesis
    // permutations using n open brackets. or ways to travel to matrix top from bottom without going center.

    public int nthBellNumber(int n){//ways to partition a set containing n elements
        if(n == 0)
            return 1;
        int[][] bellMatrix = new int[n][n];
        bellMatrix[0][0] = 1;
        for(int i = 1;i<n;i++){
            int col = 0;
            while(col < n){
                if(col == 0)
                    bellMatrix[i][col] = bellMatrix[i-1][i-1];
                else
                    bellMatrix[i][col] = bellMatrix[i][col-1]+bellMatrix[i-1][col-1];
                col++;
            }
        }
        return bellMatrix[n-1][0];
    }
}
