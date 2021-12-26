package class01;

import java.util.Arrays;

/**
 * @author lizihao
 * @date 2021/12/26 22:42
 * <p>
 * 题目描述：
 * 给定一个有序数组arr，代表坐落在X轴上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 */
public class Code01_CordCoverMaxPoint {
    public static int maxPoint1(int[] arr, int k) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearestIndex = findNearestIndex(arr, i, arr[i] - k);
            res = Math.max(res, i - nearestIndex + 1);
        }
        return res;
    }

    private static int findNearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;

    }

    public static int maxPoint2(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int n = arr.length;
        int max = 0;
        while (left < n) {
            while (right < n && arr[right] - arr[left] <= k) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    public static int test(int[] arr, int k) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= k) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }
    //对数器

    /**
     * 产生一个随机数组
     *
     * @param len 长度
     * @param max 最大值
     * @return
     */
    public static int[] generateRandomArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int l = (int) (Math.random() * max);
            int[] arr = generateRandomArray(len, max);
            int ans1 = maxPoint1(arr, l);
            int ans2 = maxPoint2(arr, l);
            int ans3 = test(arr, l);
            if (ans1!=ans2||ans2!=ans3){
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
