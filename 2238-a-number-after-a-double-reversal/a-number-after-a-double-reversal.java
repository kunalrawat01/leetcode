class Solution {
    public boolean isSameAfterReversals(int num) {
      int ori = num;
      int res = 0;
      while (num>0){
        int digit = num % 10;
        res = res * 10 + digit;
        num=num/10;
      }
     int res2 = 0;
        while (res>0){
        int digit1 = res % 10;
        res2 = res2 * 10 + digit1;
        res=res/10;
      }
      if (ori == res2){
        return true ;
      }
    return false ;
    }
}