package top.trial.leetcode.linkedList;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/20
 * @Version 1.0
 */
public class Number0143_ReorderList {
    @Test
    public void solution() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderListOne(head);
        System.out.println(LinkedListUtils.goThroughLinkedList(head));
    }

    private void reorderListOne(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode curr = head;
        if (curr == null)
            return;
        while (curr.next != null) {
            deque.addFirst(curr.next);
            curr = curr.next;
        }
        curr = head;
        while (!deque.isEmpty()) {
            curr.next = deque.pollFirst();
            curr = curr.next;
            if (!deque.isEmpty()) {
                curr.next = deque.pollLast();
                curr = curr.next;
            }
        }
        curr.next = null;
    }
}
