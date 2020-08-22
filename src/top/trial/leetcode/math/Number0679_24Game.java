package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 * <p>
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 * <p>
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/24-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/22
 * @Version 1.0
 */
public class Number0679_24Game {
    @Test
    public void solution() {
        int[] nums = {4, 1, 8, 7};
        boolean result = judgePoint24One(nums);
        System.out.println(result);
        Assert.assertTrue(result);
    }

    /**
     * 使用回溯，从四个数中随机取2个，从四个操作符随机选一个进行运算（减和除区分前后），共六种，计算完放回成为三个数，然后三个数随机取2个，从四个操作符随机选一个进行运算，然后得到的结果继续从四个操作符随机选一个进行运算
     *
     * @param nums int[]
     * @return boolean
     */
    private boolean judgePoint24Two(int[] nums) {


        List<Float> floatNums = new ArrayList<>();
        for (int num : nums) {
            floatNums.add((float) num);
        }
        return is24(floatNums);
    }

    private boolean is24(List<Float> floatNums) {
        if (floatNums.size() == 0)
            return false;
        if (floatNums.size() == 1)
            return Math.abs(floatNums.get(0) - 24) < 0.0001;
        int size = floatNums.size();//2或3或4
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i != j) {//随机取了2个坐标
                    List<Float> newFloatNums = new ArrayList<>();//存放计算结果的list
                    //将floatNums中未选中的数字加入newFloatNums
                    for (int k = 0; k < size; ++k) {
                        if (k != i && k != j)
                            newFloatNums.add(floatNums.get(k));
                    }
                    //计算操作
                    for (int m = 0; m < 6; ++m) {
                        if (m == 0) {//加
                            newFloatNums.add(floatNums.get(i) + floatNums.get(j));
                        } else if (m == 1) {//前减后
                            newFloatNums.add(floatNums.get(i) - floatNums.get(j));
                        } else if (m == 2) {//后减前
                            newFloatNums.add(floatNums.get(j) - floatNums.get(i));
                        } else if (m == 3) {//乘
                            newFloatNums.add(floatNums.get(i) * floatNums.get(j));
                        } else if (m == 4) {//前除以后
                            newFloatNums.add(floatNums.get(i) / floatNums.get(j));
                        } else {//后除以前
                            newFloatNums.add(floatNums.get(j) / floatNums.get(i));
                        }
                        if (is24(newFloatNums)) {
                            return true;
                        }
                        newFloatNums.remove(newFloatNums.size() - 1);//删除计算操作的结果，重新计算
                    }
                }
            }
        }
        return false;
    }


    /**
     * 怎么判断24点？分解为四个数，做三次运算，可能的运算顺序4*3*2，三次运算可能性是4^3，全部暴力1536种解法可行否？茫茫多重复计算,且解法有误！！！
     *
     * @param nums int[]
     * @return boolean
     */
    private boolean judgePoint24One(int[] nums) {

        char[] oper = {'+', '-', '*', '/'};

        List<Integer[]> seqs = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j != i) {
                    for (int l = 0; l < 4; ++l) {
                        if (l != i && l != j) {
                            for (int s = 0; s < 4; ++s) {
                                if (s != i && s != j && s != l)
                                    seqs.add(new Integer[]{i, j, l, s});
                            }
                        }
                    }
                }
            }
        }

        //System.out.println(seqs.size());

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int l = 0; l < 4; ++l) {
                    for (Integer[] seq : seqs) {
                        float result1 = calc(nums[seq[0]], nums[seq[1]], oper[i]);
                        float result2 = calc(nums[seq[1]], nums[seq[0]], oper[i]);

                        float result3 = calc(result1, nums[seq[2]], oper[j]);
                        float result4 = calc(nums[seq[2]], result1, oper[j]);
                        float result5 = calc(result2, nums[seq[2]], oper[j]);
                        float result6 = calc(nums[seq[2]], result2, oper[j]);

                        float result7 = calc(result3, nums[seq[3]], oper[l]);
                        float result8 = calc(result4, nums[seq[3]], oper[l]);
                        float result9 = calc(result5, nums[seq[3]], oper[l]);
                        float result10 = calc(result6, nums[seq[3]], oper[l]);
                        float result11 = calc(nums[seq[3]], result3, oper[l]);
                        float result12 = calc(nums[seq[3]], result4, oper[l]);
                        float result13 = calc(nums[seq[3]], result5, oper[l]);
                        float result14 = calc(nums[seq[3]], result6, oper[l]);
                        if (Math.abs(result7 - 24.0f) < 0.001 || Math.abs(result8 - 24.0f) < 0.001 || Math.abs(result9 - 24.0f) < 0.001 || Math.abs(result10 - 24.0f) < 0.001 || Math.abs(result11 - 24.0f) < 0.001 || Math.abs(result12 - 24.0f) < 0.001 || Math.abs(result13 - 24.0f) < 0.001 || Math.abs(result14 - 24.0f) < 0.001)
                            return true;
                    }
                }
            }
        }


        return false;
    }

    private float calc(float num1, float num2, char c) {

        float result;
        switch (c) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            default:
                result = num1 / num2;
                break;
        }
        return result;

    }
}
