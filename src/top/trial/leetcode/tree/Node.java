package top.trial.leetcode.tree;

/**
 * 带next指针的node树节点
 *
 * @Author gaoyx1469
 * @Date 2020/9/28
 * @Version 1.0
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
