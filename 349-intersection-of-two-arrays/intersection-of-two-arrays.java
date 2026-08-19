class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> set = new HashSet<>();
            for(int num1 : nums1){
                set.add(num1);
            }
             HashSet<Integer> ans = new HashSet<>();
             for(int num2 : nums2){
             if(set.contains(num2)){
                ans.add(num2);
           }
        }
        int[] result = new int[ans.size()];
        int i=0;
        for(int num : ans){
                result[i] = num;
                i++;
        }
        return result;
    }
}