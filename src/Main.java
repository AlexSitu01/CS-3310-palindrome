import static java.lang.System.nanoTime;

public class Main {
    public static void main(String[] args) {
        long startTime;
        long endTime;

        String s = "abbabbcd";
        int n = s.length();
        int[][] table = new int[n][n];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table.length; j++){
                table[i][j] = -1;
            }
        }
        System.out.println("Test String: "+s);
        System.out.println();
        startTime = nanoTime();
        System.out.println("Recursive method answer: "+findLongestPalindrome(s, 0, n-1));
        endTime = nanoTime();
        System.out.println("CPU time: "+ (endTime-startTime));

        System.out.println();

        startTime = nanoTime();
        System.out.println("Memoization method answer: "+memoFindLongestPalindrome(s, 0, n-1, table));
        endTime = nanoTime();
        System.out.println("CPU time: "+ (endTime-startTime));
    }

    public static int findLongestPalindrome(String s, int l, int r){
        if(l ==r){
            return 1;
        }
        if(s.charAt(l)==s.charAt(r) && l+1 == r){
            return 2;
        }
        if(s.charAt(l) == s.charAt(r)){
            return findLongestPalindrome(s, l+1, r-1) + 2;
        }
        else{
            return Math.max(findLongestPalindrome(s,l, r -1), findLongestPalindrome(s, l+1, r));
        }
    }

    public static int memoFindLongestPalindrome(String s, int l, int r, int[][] table){
        if(l == r){
            return table[l][r] = 1;
        }

        if(s.charAt(l)==s.charAt(r) && l+1==r){
            return table[l][r] = 2;
        }

        //substring has already been calculated
        if(table[l][r] != -1){
            return table[l][r];
        }

        if(s.charAt(l)==s.charAt(r)){
            return table[l][r] = memoFindLongestPalindrome(s, l+1, r-1, table) + 2;
        }
        else{
            return table[l][r] = Math.max(memoFindLongestPalindrome(s, l, r-1, table), memoFindLongestPalindrome(s, l+1, r, table));
        }

    }
}