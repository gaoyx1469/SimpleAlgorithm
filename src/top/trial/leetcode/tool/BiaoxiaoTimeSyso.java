package top.trial.leetcode.tool;

import java.util.Random;

public class BiaoxiaoTimeSyso {

    public static void main(String[] args) {
        Random random = new Random();
        int num = 62;
        //getWorkTime(random, num);
        getTaxiPrice(random, num);

    }

    private static void getTaxiPrice(Random random, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(getRandomInt(random, 10, 80));
        }
    }

    private static void getWorkTime(Random random, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(getTime(random));
        }
    }

    /**
     * 获取随机整数，给定范围
     *
     * @param random Random
     * @param wid    int
     * @param start  int
     * @return int
     */
    private static int getRandomInt(Random random, int wid, int start) {
        return random.nextInt(wid) + start;
    }

    /**
     * 整数补齐
     *
     * @param num int
     * @return String
     */
    private static String getTString(int num) {

        if (num < 10) {
            return "0" + num;
        }
        return "" + num;
    }

    /**
     * 获取时间
     *
     * @param random Random
     * @return String
     */
    private static String getTime(Random random) {
        int hour = getRandomInt(random, 2, 21);
        int min = getRandomInt(random, 60, 0);
        int add = getRandomInt(random, 7, 3);
        String ft = hour + ":" + getTString(min);
        int minadd = min + add;
        if ((minadd) >= 60) {
            hour++;
            minadd -= 60;
        }
        String at = hour + ":" + getTString((minadd));

        return ft + "	" + at;
    }
}
