package top.trial.leetcode.math;

import org.junit.Test;

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * <p>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * <p>
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * <p>
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divisor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/24
 * @Version 1.0
 */
public class Number1025_DivisorGame {

    @Test
    public void solution() {
        System.out.println(divisorGameOne(9));
    }

    /**
     * 其实，奇数都是输，偶数都是赢。。。。
     *
     * @param N
     * @return
     */
    private boolean divisorGameTwo(int N) {
        return N % 2 == 0;
    }


    /**
     * 动态规划
     *
     * @param N
     * @return
     */
    private boolean divisorGameOne(int N) {

        boolean[] result = new boolean[N];
        if (N > 1) {
            result[1] = true;
        }

        for (int i = 2; i < N; ++i) {
            //求解result[i]
            //求i+1的所有因数
            boolean temp = false;
            int j = 1;
            while (!temp && j <= (int) Math.sqrt(i + 1)) {
                if ((i + 1) % j == 0) {//可整除
                    temp = !result[i - j];
                }
                ++j;
            }
            result[i] = temp;
        }

        return result[N - 1];
    }
}
