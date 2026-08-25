class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[left] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return nums[left];
            }
            left++;
        }
        return -1;
    }
}