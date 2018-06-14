public class Median_of_Two_Sorted_Arrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length, size2 = nums2.length;
        int[] all = new int[size1 + size2];
        int i = 0, j = 0, k = 0;
        for(; i + j < size1 + size2;){
            int a = i == size1 ? 0 : nums1[i];
            int b = j == size2 ? 0 : nums2[j];

            if (i == size1){
                all[k++] = b;
                j++;
            } else if(j == size2){
                all[k++] = a;
                i++;
            } else if(a < b){
                all[k++] = a;
                i++;
            } else if (a > b){
                all[k++] = b;
                j++;
            } else {
                all[k++] = a;
                all[k++] = b;
                i++;
                j++;
            }
        }
        int flag = (size1 + size2) % 2;
        if(flag == 1){
            int m = (size1 + size2) / 2;
            return (all[m] + all[m]) / 2.0;
        } else {
            int m = (size1 + size2) / 2;
            int n = m - 1;
            return (all[m] + all[n]) / 2.0;
        }
    }


    public static void main(String[] args){
//        int[] nums1 = new int[]{1,3};
//        int[] nums2 = new int[]{2};

        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3, 4};

        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }

}
