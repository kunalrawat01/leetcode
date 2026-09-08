class Solution {
    public int maxVowels(String s, int k) {
          int left = 0;
          int count = 0;
          int max = Integer.MIN_VALUE;
          for(int right = 0;right<s.length();right++){
                 if("aeiouAEIOU".indexOf(s.charAt(right)) != -1){
                        count++;
                      }
                   if(right - left + 1 == k){
                       max = Math.max(max,count);
                 if("aeiouAEIOU".indexOf(s.charAt(left)) != -1){
                    count--;
                 }
                 left++;
              }
          }
          return max;
    }
}