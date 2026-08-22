class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        while(n>0){
            int digit = n%10;
            sum+=digit;
            n/=10;
        }
        n = original;
        int product = 1;
        while(n>0){
            int digit2 = n%10;
            product*=digit2;
                n/=10;
        }
        return (original % (sum + product) == n);
         
    }
}