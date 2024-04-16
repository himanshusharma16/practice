package test;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        int[] arr = {1,3,7,7,4,3,5};
        new Test1().convert("PAYPALISHIRING",3);
        System.out.println(new Test1().findMinimumCharacters("sdmaddzsfonsd","amazon"));//findMaximumSum(arr,3));
    }

    public String convert(String s, int numRows) {
        StringBuilder[] res = new StringBuilder[numRows];
        int index = 0; boolean direction = false;
        for(int i = 0 ; i < s.length() ; i++){
            if(res[index] == null)
                res[index] = new StringBuilder();
            res[index].append(s.charAt(i));
            if(index == numRows-1 || index == 0){
                direction = !direction;
            }
            if(direction)
                index++;
            else
                index--;
        }
        String result = "";
        for(var sb : res){
            result = result.concat(sb.toString());
        }
        return result;
    }

    public int findMinimumCharacters(String search,String result){
      int match = 0;
      for(char c : result.toCharArray()){
          int in = search.indexOf(c);
          if(in >= 0){
              match++;
              search = search.substring(in);
          } else
              break;
      }
      return result.length() - match;
    }

    public long findMaximumSum(int[] stockPrice , int k){
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> duplicates = new HashSet<>();
        long max = 0;
        if(k>stockPrice.length)
            return -1;
        int sum = 0;
        for(int i = 0;i<k;i++){
            if(map.get(stockPrice[i]) != null){
                duplicates.add(stockPrice[i]);
            }
            sum += stockPrice[i];
            map.put(stockPrice[i],map.getOrDefault(stockPrice[i],0)+1);
        }

        if(duplicates.isEmpty())
            max = Math.max(max,sum);

        int start = 0;
        for(int j=k; j< stockPrice.length; j++){
            int current = stockPrice[j];
            int startFreq = map.get(stockPrice[start]);
            if(startFreq == 1) {
                map.remove(stockPrice[start]);
            }
            else {
                map.put(stockPrice[start], startFreq - 1);
                startFreq--;
                if(startFreq == 1)
                    duplicates.remove(stockPrice[start]);
            }
            sum -= stockPrice[start];
            start++;
            if(map.get(current) != null){
                duplicates.add(current);
            }
            sum += current;
            map.put(current,map.getOrDefault(current,0)+1);
            if(duplicates.isEmpty())
                max = Math.max(max,sum);
        }
        if(max == 0)
            return -1;
        return max;
    }
}
