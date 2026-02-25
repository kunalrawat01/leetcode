class Solution {
    public int thirdMax(int[] nums) {
        Long max1 = null, max2 = null, max3 = null;
        
        for (int num : nums) {
            long n = num;
            if ((max1 != null && n == max1) || 
                (max2 != null && n == max2) || 
                (max3 != null && n == max3)) {
                continue; 
            }
            
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        
        return max3 == null ? max1.intValue() : max3.intValue();
    }
}