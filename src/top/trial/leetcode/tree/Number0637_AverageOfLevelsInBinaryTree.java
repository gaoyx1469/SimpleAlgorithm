package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * <p>
 * 示例 1：
 * 输入：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * <p>
 * 提示：
 * <p>
 * 节点值的范围在32位有符号整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/7/23
 * @Version 1.0
 */
public class Number0637_AverageOfLevelsInBinaryTree {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<Double> result = averageOfLevelsInBinaryTree(root);
        System.out.println(result);
    }

    /**
     * 使用队列？双队列广度优先搜索
     *
     * @param root TreeNode
     * @return
     */
    private List<Double> averageOfLevelsInBinaryTree(TreeNode root) {

        //遍历root
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        List<Double> list = new ArrayList<>();
        int count = 0;
        double sum = 0.0;
        queue1.add(root);
        while (!(queue1.isEmpty() && queue2.isEmpty())) {
            while (!queue1.isEmpty()) {
                ++count;
                sum += queue1.peek().val;
                if (queue1.peek().right != null) {
                    queue2.add(queue1.peek().right);
                }
                if (queue1.peek().left != null) {
                    queue2.add(queue1.peek().left);
                }
                queue1.poll();
            }
            if (count != 0)
                list.add(sum / ((double) count));
            count = 0;
            sum = 0.0;
            while (!queue2.isEmpty()) {
                ++count;
                sum += queue2.peek().val;
                if (queue2.peek().right != null) {
                    queue1.add(queue2.peek().right);
                }
                if (queue2.peek().left != null) {
                    queue1.add(queue2.peek().left);
                }
                queue2.poll();
            }
            if (count != 0)
                list.add(sum / ((double) count));
            count = 0;
            sum = 0;
        }

        return list;
    }
}
