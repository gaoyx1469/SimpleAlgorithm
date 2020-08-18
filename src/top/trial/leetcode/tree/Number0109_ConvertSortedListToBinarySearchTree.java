package top.trial.leetcode.tree;

import org.junit.Test;
import top.trial.leetcode.utils.TreeNodeUtil;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/8/18
 * @Version 1.0
 */
public class Number0109_ConvertSortedListToBinarySearchTree {
    @Test
    public void solution() {
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);
        TreeNode root = sortedListToBSTTwo(head);
        System.out.println(TreeNodeUtil.getStringOfTreeNodeAccordingDepth(root));
    }

    ListNode tempNode;

    /**
     * 中序遍历
     *
     * @param head ListNode
     * @return TreeNode
     */
    private TreeNode sortedListToBSTTwo(ListNode head) {

        int len = getLength(head);
        tempNode = head;
        TreeNode root = buildTree(0, len - 1);

        return root;
    }

    private TreeNode buildTree(int head, int tail) {
        if (head > tail)
            return null;
        int mid = head + (tail - head + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(head, mid - 1);
        root.val = tempNode.val;
        tempNode = tempNode.next;
        root.right = buildTree(mid + 1, tail);
        return root;
    }

    private int getLength(ListNode head) {
        int i = 0;
        while (head != null) {
            ++i;
            head = head.next;
        }
        return i;
    }

    /**
     * 链表变BST，每次getMidListNode耗时，看看是否能优化
     *
     * @param head ListNode
     * @return TreeNode
     */
    private TreeNode sortedListToBSTOne(ListNode head) {
        return getTree(head, null);
    }

    private TreeNode getTree(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode midListNode = getMidListNode(head, tail);
        TreeNode root = new TreeNode(midListNode.val);
        root.left = getTree(head, midListNode);//包左不包右
        root.right = getTree(midListNode.next, tail);//包左不包右

        return root;
    }

    private ListNode getMidListNode(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
