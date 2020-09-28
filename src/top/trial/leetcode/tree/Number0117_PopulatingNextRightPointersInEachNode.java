package top.trial.leetcode.tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author gaoyx1469
 * @Date 2020/9/28
 * @Version 1.0
 */
public class Number0117_PopulatingNextRightPointersInEachNode {
    @Test
    public void solution() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(7);
        connectOne(root);
    }

    //使用两个队列如何？可行
    //还可以使用一个队列，每次判断一下元素个数就行！！！
    //还可以利用上一层已经建立的next指针，不需要队列存储就可以遍历下一层！！！
    private Node connectOne(Node root) {
        if (root == null)
            return null;
        Deque<Node> deque1 = new LinkedList<>();
        Deque<Node> deque2 = new LinkedList<>();
        deque1.add(root);
        while (!deque1.isEmpty()) {
            while (!deque1.isEmpty()) {
                Node node = deque1.pollFirst();
                if (node.left != null) {
                    deque2.offerLast(node.left);
                }
                if (node.right != null) {
                    deque2.offerLast(node.right);
                }
                if (!deque1.isEmpty()) {
                    node.next = deque1.getFirst();
                }
            }
            while (!deque2.isEmpty()) {
                Node node = deque2.pollFirst();
                if (node.left != null) {
                    deque1.offerLast(node.left);
                }
                if (node.right != null) {
                    deque1.offerLast(node.right);
                }
                if (!deque2.isEmpty()) {
                    node.next = deque2.getFirst();
                }
            }
        }
        return root;
    }
}
