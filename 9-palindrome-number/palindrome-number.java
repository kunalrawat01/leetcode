class Solution {
    public boolean isPalindrome(int x) {
        int original = x;
        int rev = 0;
            while(x>0){
                int temp = x%10;
                rev = rev*10+temp;
                x/=10;
            }
            if(rev == original){
                return true;
            }else{
                return false;
            }

    }
}