package test.DP.numbers;

//this is the number of ways to partition a set containing n elements.
public class BellNumbers {

    public static void main(String[] args) {
        //Given an index, print the bell number
        int index = 4;
        System.out.println(index+" bell number is - "+new BellNumbers().getBellNumber(1));
        System.out.println(index+" bell number is - "+new BellNumbers().getBellNumber(2));
        System.out.println(index+" bell number is - "+new BellNumbers().getBellNumber(3));
        System.out.println(index+" bell number is - "+new BellNumbers().getBellNumber(index));
        System.out.println(index+" bell number is - "+new BellNumbers().getBellNumber(5));

    }

    private int getBellNumber(int n) {
        int[][] belldp = new int[n+1][n+1];
        belldp[0][0] = 1;
        if(n == 0)
            return belldp[0][0];
        belldp[1][0] = 1;
        for(int i = 1;i<n+1;i++){
            for(int j = 0; j<=i;j++){
                if(j == 0)
                    belldp[i][j] = belldp[i-1][i-1];
                else
                    belldp[i][j] = belldp[i][j-1]+belldp[i-1][j-1];
            }
        }
        return belldp[n][0];
    }
}
