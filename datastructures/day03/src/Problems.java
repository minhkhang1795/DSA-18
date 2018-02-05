import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    // TODO: O(N)
    public static List<Integer> removeKDigits(int[] A, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int a : A) {
            while (k != 0 && !stack.isEmpty() && stack.peek() > a) {
                stack.pop();
                k--;
            }
            stack.push(a);
        }
        while (k-- > 0)
            stack.pop();
        return stack;
    }

    // TODO: O(N^2)
    public static List<Integer> removeKDigits2(int[] A, int k) {
        int removed = -2;
        List<Integer> l = new LinkedList<>();
        int prev = 0;
        for (int i = 1; i < A.length; i++) {
            while (prev >= 0 && (A[prev] == removed || A[prev] > A[i]) && k > 0) {
                if (A[prev] != removed)
                    k--;
                A[prev] = removed;
                prev--;
            }
            prev = i;
        }

        while (prev - 1 >= 0 && (A[prev] == -2 || A[prev] > A[prev - 1]) && k > 0) {
            if (A[prev] != removed)
                k--;
            A[prev] = removed;
            prev--;
        }
        for (int a : A)
            if (a != removed)
                l.add(a);
        return l;
    }

    // TODO: O(N)
    public static boolean isPalindrome(Node n) {
        Node head = n;
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        head = n;
        Node mid = n;
        for (int i = 0; i <= size / 2 && size != 0; i++)
            mid = mid.next;

        mid = reverse(mid);
        while (mid != null) {
            if (head.val != mid.val)
                return false;
            head = head.next;
            mid = mid.next;
        }
        return true;
    }

    private static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // TODO: O(N)
    public static String infixToPostfix(String s) {
        StringJoiner result = new StringJoiner(" ");
        Queue<Character> numbers = new LinkedList<>();
        Stack<Character> operations = new Stack<>();
        int numParens = 0;

        for (char c : s.toCharArray()) {
            if (c == ')') {
                numParens--;
                numbers.add(operations.pop());
            } else if (c == '(') {
                numParens++;
            } else if (c >= '0' && c <= '9') {
                numbers.add(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (numParens == 0 && !operations.isEmpty())
                    numbers.add(operations.pop());
                operations.push(c);
            }
        }
        numbers.addAll(operations);

        while (!numbers.isEmpty())
            result.add(numbers.remove() + "");

        return result.toString();
    }

}
