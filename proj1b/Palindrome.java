public class Palindrome {
	public Deque<Character> wordToDeque(String word) {
		Deque<Character> result = new LinkedListDeque<>();
		int size = word.length();
		for (int i = 0; i < size; i++) {
			result.addLast(word.charAt(i));
		}
		return result;
	}

	public boolean isPalindrome(String word) {
		if (word == null) {
			return true;
		}
		return isPalindromeHelper(word, 0);
	}

	private boolean isPalindromeHelper(String word, int start) {
		int size = word.length();
		int end = size - 1 - start;
		if (start >= end) {
			return true;
		} else if (word.charAt(start) != word.charAt(end)) {
			return false;
		} else {
			return isPalindromeHelper(word, start + 1);
		}
	}

	public boolean isPalindrome(String word, CharacterComparator cc) {
		if (word == null || word.length() <= 1) {
			return true;
		}
		int size = word.length();
		int start = 0, end = size - 1;
		while (start < end) {
			if (!cc.equalChars(word.charAt(start), word.charAt(end))) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
