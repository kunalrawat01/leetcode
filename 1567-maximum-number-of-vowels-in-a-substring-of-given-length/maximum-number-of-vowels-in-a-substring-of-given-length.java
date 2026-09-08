class Solution {
    public int maxVowels(String s, int k) {
          int left = 0;
          int count = 0;
          int max = 0;
         for(int right = 0;right < k;right++){
            if("aeiouAEIOU".indexOf(s.charAt(right)) != -1){
                count++;
            }
         }
         max = count;
            for(int right = k;right<s.length();right++){
                    if("aeiouAEIOU".indexOf(s.charAt(right)) != -1){
                count++;
            }
                if("aeiouAEIOU".indexOf(s.charAt(left)) != -1){
                    count--;
                    }
                    left++;
                    max = Math.max(max,count);
                 }
         return max;
    }
}