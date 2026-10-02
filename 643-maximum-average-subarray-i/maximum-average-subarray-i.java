class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double[] avg = new double[n-k+1];
        int left = 0;
        int sum = 0;
        double maxavg = Double.NEGATIVE_INFINITY;
        for(int right = 0;right<n;right++){
            sum+=nums[right];
            if(right-left+1==k){
                avg[left] = (double)sum/k;
                if(avg[left]>maxavg){
                    maxavg = avg[left];
                }
                sum-=nums[left];
                left++;
            }
        }
        return maxavg;
    }
}