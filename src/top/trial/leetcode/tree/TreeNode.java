package top.trial.leetcode.tree;

/**
 * 二叉树结构定义类
 * <p>
 * 属性增加public修饰，暂时忽略安全性，以保证其它包中的类可以访问
 *
 * @author Gaoyx
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
