public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); ++i) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    /**
     * Any word of length 1 or 0 is a palindrome
     * ‘A’ and ‘a’ should not be considered equal
     *
     * @return true if the given word is a palindrome, and false otherwise
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() >= 2) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() >= 2) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
