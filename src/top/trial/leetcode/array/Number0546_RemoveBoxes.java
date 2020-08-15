package top.trial.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 * <p>
 * 示例：
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-boxes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/15
 * @Version 1.0
 */
public class Number0546_RemoveBoxes {
    @Test
    public void solution() {
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        int expected = 23;
        int result = removeBoxesTwo(boxes);
        Assert.assertEquals(expected, result);
    }

    /**
     * 既然超时，优化一下，使用动态规划，参考官方题解
     *
     * @param boxes int[]
     * @return int
     */
    private int removeBoxesTwo(int[] boxes) {

        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);

    }

    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;//不合理输入
        if (dp[l][r][k] != 0) return dp[l][r][k];//计算过了
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);//不再拼接，直接拿后面的
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }

    /**
     * 看着像动态规划？先用暴力法，结果没错，但铁定超时丫
     *
     * @param boxes int[]
     * @return int
     */
    private int removeBoxesOne(int[] boxes) {

        if (boxes.length == 0)
            return 0;
        if (boxes.length == 1)
            return 1;

        int count = 1;
        int result = 0;

        for (int i = 1; i < boxes.length; ++i) {
            if (boxes[i] == boxes[i - 1]) {
                count++;
            } else {

                int[] newArray = new int[boxes.length - count];
                for (int j = 0; j < i - count; ++j) {
                    newArray[j] = boxes[j];
                }
                for (int j = i - count; j < newArray.length; ++j) {
                    newArray[j] = boxes[j + count];
                }
                result = Math.max(result, removeBoxesOne(newArray) + count * count);
                count = 1;
            }
        }
        if (boxes[boxes.length - 1] == boxes[boxes.length - 2]) {
            if (boxes.length == count) {
                result = count * count;
            } else {
                int[] newArray = new int[boxes.length - count];
                for (int j = 0; j < newArray.length; ++j) {
                    newArray[j] = boxes[j];
                }
                result = Math.max(result, removeBoxesOne(newArray) + count * count);
            }
        } else {
            int[] newArray = new int[boxes.length - 1];
            for (int j = 0; j < newArray.length; ++j) {
                newArray[j] = boxes[j];
            }
            result = Math.max(result, removeBoxesOne(newArray) + count * count);
        }

        //removeBoxesI();
        return result;
    }
}
