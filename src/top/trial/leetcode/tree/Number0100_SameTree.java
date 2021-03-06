package top.trial.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * 输出: false
 * 示例 3:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/7
 * @Version 1.0
 */
public class Number0100_SameTree {
    @Test
    public void solution() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left = new TreeNode(1);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.left = new TreeNode(2);

        Assert.assertFalse(isSameTree(root1, root2));

    }

    /**
     * DFS和BFS嘛，都行不是
     *
     * @param p TreeNode
     * @param q TreeNode
     * @return boolean
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p != null && q != null && p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
}
