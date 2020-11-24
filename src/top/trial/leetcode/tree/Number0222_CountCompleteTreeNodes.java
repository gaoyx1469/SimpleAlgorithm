package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例:
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * 输出: 6
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/11/24
 * @Version 1.0
 */
public class Number0222_CountCompleteTreeNodes {
    @Test
    public void solution() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        int expected = 6;
        int result = countNodes(root);
        Assert.assertEquals(expected, result);
    }

    private int countNodes(TreeNode root) {

        if (root == null)
            return 0;


        //获取最高层数
        int level = getLevel(root);
        if (level == 1)
            return 1;
        int[] arr = new int[level - 1];
        arr[0] = 1;
        for (int i = 1; i < arr.length; ++i) {
            arr[i] = arr[i - 1] * 2;
        }

        int start = 1;
        int end = (int) Math.pow(2, level - 1) - 1;
        int mid = end - (end - start) / 2;
        while (start <= end) {
            TreeNode temp = root;
            for (int i = level - 1; i > 0; --i) {
                temp = ((mid & arr[i - 1]) == arr[i - 1]) ? temp.right : temp.left;
            }
            if (temp == null)
                end = mid - 1;
            else {
                start = mid + 1;
            }
            mid = end - (end - start) / 2;
        }
        return ((int) Math.pow(2, level - 1)) - 1 + start;
    }

    private int getLevel(TreeNode root) {
        int result = 1;
        TreeNode curr = root;
        while (curr.left != null) {
            result++;
            curr = curr.left;
        }

        return result;
    }
}
