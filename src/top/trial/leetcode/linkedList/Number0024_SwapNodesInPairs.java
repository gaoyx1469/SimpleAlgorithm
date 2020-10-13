package top.trial.leetcode.linkedList;

import org.junit.Test;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/13
 * @Version 1.0
 */
public class Number0024_SwapNodesInPairs {
    @Test
    public void solution() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head = swapPairsOne(head);
        System.out.println(LinkedListUtils.goThroughLinkedList(head));
    }

    private ListNode swapPairsOne(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode newHead = head.next;//定义新的链表头
        ListNode curr = null;//定义每个交换对的前一个结点
        ListNode front = head;//交换对的第一个结点
        ListNode behind = head.next;//交换对的第二个结点

        while (true) {
            if (curr != null)
                curr.next = behind;//将交换对前面的链表与交换对连接
            front.next = behind.next;//交换
            curr = behind.next = front;//交换
            if (front.next == null || front.next.next == null)
                break;//后面不再有交换对
            else {
                front = front.next;
                behind = front.next;
            }
        }
        return newHead;
    }
}
