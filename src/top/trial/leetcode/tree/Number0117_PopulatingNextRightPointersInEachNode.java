package top.trial.leetcode.tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
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
