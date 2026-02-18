class Solution {
    public boolean isHappy(int n) {
      int temp = n;
      while (temp != 1 && temp != 4){
           int sum = 0;
           while (temp > 0){
            int digit = temp % 10 ;
           sum += digit * digit;
            temp /= 10;
           }
           temp = sum;
      }
      return temp == 1;
    }
}