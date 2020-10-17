package top.trial.leetcode.linkedList;

import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author gaoyx1469
 * @Date 2020/10/18
 * @Version 1.0
 */
public class Number0019_RemoveNthNodeFromEndOfList {
    @Test
    public void solution() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head = removeOne(head, 2);
        System.out.println(LinkedListUtils.goThroughLinkedList(head));
    }

    /**
     * 常规方式，两次遍历，第一次计数，第二次直接删除节点
     * 若要实现一次遍历，使用快慢双指针即可，第一个指针先走n步，第二个指针开始走，当第一个指针走到末尾，第二个指针指向的就是要删除节点的前一个节点
     *
     * @param head ListNode
     * @param n    int
     * @return ListNode
     */
    private ListNode removeOne(ListNode head, int n) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        int index = len - n;
        curr = head;
        if (index == 0) {
            head = head.next;
        } else {
            while (index > 1) {
                curr = curr.next;
                index--;
            }
            curr.next = curr.next.next;
        }
        return head;

    }
}
