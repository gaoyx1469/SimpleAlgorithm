package top.trial.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有 1000 只水桶，其中有且只有一桶装的含有毒药，其余装的都是水。它们从外观看起来都一样。如果小猪喝了毒药，它会在 15 分钟内死去。
 * 问题来了，如果需要你在一小时内，弄清楚哪只水桶含有毒药，你最少需要多少只猪？
 * <p>
 * 回答这个问题，并为下列的进阶问题编写一个通用算法。
 * <p>
 * 进阶:
 * 假设有 n 只水桶，猪饮水中毒后会在 m 分钟内死亡，你需要多少猪（x）就能在 p 分钟内找出 “有毒” 水桶？这 n 只水桶里有且仅有一只有毒的桶。
 * <p>
 * 提示：
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 m 分钟的冷却时间。在这段时间里，只允许观察，而不允许继续饮水。
 * 任何给定的桶都可以无限次采样（无限数量的猪）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/poor-pigs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/20
 * @Version 1.0
 */
public class Number0458_PoorPigs {

    @Test
    public void solution() {
        int buckets = 1000;
        int minutesToDie = 15;
        int minutesToTest = 60;
        int expected = 5;
        //int result = poorPigOne(buckets, minutesToDie, minutesToTest);
        int result = poorPigTwo(buckets, minutesToDie, minutesToTest);
        Assert.assertEquals(expected, result);
    }

    /**
     * x只猪可以测（最大可测次数+1）^x桶水，即(minutesToTest/minutesToDie+1)^x >=  buckets，推导出x >= lg buckets以(minutesToTest/minutesToDie+1)为底，
     * 为方便计算，换算成以10为底，则x >= log buckets / log (minutesToTest/minutesToDie+1)，使用Math.log计算log，使用Math.ceil向上取整
     *
     * @param buckets       水桶数
     * @param minutesToDie  冷却时间
     * @param minutesToTest 总实验时间
     * @return int 最少小猪数量
     */
    private int poorPigTwo(int buckets, int minutesToDie, int minutesToTest) {

        int times = minutesToTest / minutesToDie;//可测试次数
        return (int) Math.ceil(Math.log(buckets) / Math.log(times + 1));
    }

    /**
     * Noooooo，这是错误解法，局限在了每次只死一只猪！实际上x只猪可以测s^x桶水
     *
     * @param buckets       水桶数
     * @param minutesToDie  冷却时间
     * @param minutesToTest 总实验时间
     * @return int 最少小猪数量
     */
    private int poorPigOne(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets < 2)
            return 0;//就一桶，不需要猪
        int times = minutesToTest / minutesToDie;//可测试次数
        if (times < 1)
            return -1;//异常

        int result = 1;//最少的猪的数量
        int maxBuckets = 2;//result数量只猪，可以最多测出的桶数
        int di;
        while (maxBuckets < buckets) {
            di = Math.abs(times - result);
            maxBuckets = 2 + di;
            if (result < times) {
                for (int i = 1; i < result; ++i) {
                    maxBuckets = maxBuckets * (i + 1) + 1;
                }
            } else {
                for (int i = 1; i < times; ++i) {
                    maxBuckets = maxBuckets * (di + i + 1) + 1;
                }
            }
            ++result;
        }
        return --result;
    }
}
