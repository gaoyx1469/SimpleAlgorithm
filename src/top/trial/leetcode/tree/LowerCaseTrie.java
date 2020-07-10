package top.trial.leetcode.tree;

/**
 * 构造一个纯小写字母字典树供使用
 * 
 * @author Gaoyx
 *
 */
public class LowerCaseTrie {

	public LowerCaseTrie[] next;
	public boolean isEnd;

	public LowerCaseTrie() {
		next = new LowerCaseTrie[26];
		isEnd = false;
	}

	/**
	 * @param s
	 */
	public void insert(String s) {
		LowerCaseTrie curPos = this;
		for (int i = s.length() - 1; i >= 0; --i) {
			int t = s.charAt(i) - 'a';
			if (curPos.next[t] == null) {
				curPos.next[t] = new LowerCaseTrie();
			}
			curPos = curPos.next[t];
		}
		curPos.isEnd = true;
	}

}
