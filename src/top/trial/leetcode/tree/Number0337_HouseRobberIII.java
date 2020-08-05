package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 *      3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/5
 * @Version 1.0
 */
public class Number0337_HouseRobberIII {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        int expected = 9;
        int result = houseRobberIIITwo(root);
        Assert.assertEquals(expected, result);
    }

    /**
     * 在第一种思路出错后，更进一步思考，除了间隔一个之外，还能间隔两个，甚至某一枝还要间隔！
     * <p>
     * 这个解法可行，就是慢了点，虽然慢了点，但是代码还是挺好看的
     *
     * @param root TreeNode
     * @return int
     */
    private int houseRobberIIITwo(TreeNode root) {
        return Math.max(getValue(root, true), getValue(root, false));
    }

    /**
     * 计算当前结点累加或不累加的最大值
     *
     * @param root TreeNode
     * @param b    当前结点是否累加
     * @return int
     */
    private int getValue(TreeNode root, boolean b) {
        if (root == null)
            return 0;
        if (b) //当前结点偷取，则子结点都不可偷取，子结点的子结点可选择偷取
            return root.val + getValue(root.left, false) + getValue(root.right, false);
        else
            return Math.max(getValue(root.left, true), getValue(root.left, false)) + Math.max(getValue(root.right, true), getValue(root.right, false));

    }

    /**
     * 这道题本质不就是求奇数级和偶数级的和最大值么？显然不是！！！！！这是错误解答
     *
     * @param root TreeNode
     * @return int
     */
    private int houseRobberIIIOne(TreeNode root) {
        int[] result = new int[2];
        addResult(root, result, true);
        return Math.max(result[0], result[1]);
    }

    private void addResult(TreeNode root, int[] result, boolean flag) {
        if (root == null)
            return;
        if (flag)
            result[0] += root.val;
        else
            result[1] += root.val;
        if (root.left != null)
            addResult(root.left, result, !flag);
        if (root.right != null)
            addResult(root.right, result, !flag);
    }
}
