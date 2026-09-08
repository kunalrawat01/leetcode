class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int index = 0;
        int sum = 0;
        double maxaverage = Double.NEGATIVE_INFINITY;
        for(int right = 0;right<nums.length;right++){
               sum+=nums[right];
            if(right - left + 1 == k){
                double avg = (double)sum/k;
                maxaverage = Math.max(maxaverage,avg);
                sum-=nums[left];
                left++;
            }
        }
        return maxaverage;
    }
}