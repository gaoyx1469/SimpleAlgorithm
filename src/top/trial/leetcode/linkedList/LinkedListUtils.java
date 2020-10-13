package top.trial.leetcode.linkedList;

/**
 * @Author gaoyx1469
 * @Date 2020/10/13
 * @Version 1.0
 */
public class LinkedListUtils {

    public static String goThroughLinkedList(ListNode head) {
        ListNode curr = head;
        StringBuilder sb = new StringBuilder("[");
        while (curr != null) {
            sb.append(curr.val).append("->");
            curr = curr.next;
        }
        sb.delete(sb.lastIndexOf("->"), sb.lastIndexOf("->") + 2);
        sb.append("]");
        return sb.toString();
    }
}
