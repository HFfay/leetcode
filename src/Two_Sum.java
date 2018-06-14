public class Two_Sum {

    public static int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        int[] res = new int[2];

        for(int i=0; i<n;i++ ){
            for(int j = i;j<n;j++){
                if(i == j) continue;
                int t = nums[i] + nums[j];
                if(t == target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }

    public static void main(String[] args){
        int[] nums1 = new int[]{2,7,11,15};
        int[] res = twoSum(nums1, 9);
        System.out.println(res.toString());
    }

}
