package test;

class WordTest {

    Trie t;

    class Trie{
        boolean word = false;
        Trie[] node = new Trie[26];
    }

    public WordTest() {
        t = new Trie();
    }

    public void addWord(String word) {
        Trie curr = t;
        for(char c : word.toCharArray()) {
            if(curr.node[c-'a'] == null)
                curr.node[c-'a'] = new Trie();
            curr = curr.node[c-'a'];
        }
        curr.word = true;
    }

    public boolean search(String word) {
        Trie root = t;
        int index = 0;
        for(char c : word.toCharArray()){
            if(c == '.'){
                for(int i = 0 ; i < 26; i++){
                    if(root.node[i] != null){
                        if(word.length() == 1 && root.node[i].word)
                            return true;
                        else if(searchInTrie(root.node[i],word.substring(index+1)))
                            return true;
                    }
                }
                return false;
            } else {
                if(root.node[c-'a'] == null)
                    return false;
                else
                    root = root.node[c-'a'];
            }
            index++;
        }
        return root.word;
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] cs = s.toCharArray();
        if(s.length() == 1)
            return s;

        int max = 1, start = 0;
        for(int i = 0 ; i < s.length() ; i++)
            dp[i][i] = true;

        //2 length
        for(int i = 0 ; i < s.length()-1; i++){
            if(cs[i] == cs[i+1]){
                dp[i][i+1] = true;
                max = 2;
                start = i;
            }
        }

        //2+ length
        for(int l = 3 ; l <= s.length(); l++){
            for(int i = 0 ; i <= s.length() - l; i++){
                char cf = cs[i];
                char cl = cs[i+l-1];
                if(cf == cl && dp[i+1][i+l-2]){
                    dp[i][i+l-1] = true;
                    max = l;
                    start = i;
                }
            }
        }

        return s.substring(start,max);
    }

    private boolean searchInTrie(Trie t, String word){
        for(char c : word.toCharArray()){
            if(c == '.'){
                for(int i = 0 ; i < 26; i++){
                    if(t.node[i] != null){
                        if(word.length() == 1 && t.node[i].word)
                            return true;
                        else if(searchInTrie(t.node[i],word.substring(1)))
                            return true;
                    }
                }
                return false;
            } else {
                if(t.node[c-'a'] == null)
                    return false;
                else
                    t = t.node[c-'a'];
            }
        }
        return t.word;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

public class WordDictionary {
    public static void main(String[] args) {
        WordTest wt = new WordTest();
        System.out.println(wt.longestPalindrome("babad"));
        System.out.println(wt.longestPalindrome("cbbd"));
        wt.addWord("qsibzxaorktypkfg");
        wt.addWord("vbycuvrkbcq");
        wt.addWord("mad");
        wt.search("qsibz.aorkty.kfg");
        wt.search("bad");
        wt.search(".ad");
        wt.search("b..");
    }
}