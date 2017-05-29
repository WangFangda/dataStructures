package tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fangda.wang
 */
public class SegmentTree {


    private static final int UNINITIALIZED = -1;

    public static class Node {
        public int value = UNINITIALIZED; // used for max or min query
        public int left, right; // segment's left and right bounds
    }

    public Node[] data;

    public SegmentTree(int[] array) {
        final int length = (int) Math.pow(2, Math.ceil(Math.log(array.length)/Math.log(2)) + 1) - 1;
        data = new Node[length];
        init(0, array, 1, array.length);
    }

    private void init(int root, int array[], int start, int end) {
        data[root] = new Node();
        data[root].left = start;
        data[root].right = end;

        if (start != end) {
            final int mid = (start + end) / 2;
            final int left = root*2+1;
            final int right = root*2+2;
            init(left, array, start, mid);
            init(right, array, mid+1, end);
            data[root].value = Math.min(data[left].value, data[right].value);
        }
    }

    private void update(final int left, final int right, final int value) {
        update(left, right, 0, value);
    }

    /**
     * Passes the value to both left and right subtrees.
     *
     * @param root
     * @param value
     */
    private void passDown(final int root, final int value) {
        if (UNINITIALIZED != data[root].value && data[root].value != value) {
            data[root * 2 + 1].value = data[root * 2 + 2].value = data[root].value;
            data[root].value = UNINITIALIZED;
        }
    }

    private void update(final int left, final int right, final int root, final int value) {

        if(left == data[root].left && right == data[root].right) {
            data[root].value = value;
            return;
        }

        int mid = (data[root].left + data[root].right) >> 1;
        if (right <= mid) {
			/* [left, right] is fully in root's left subtree */
            passDown(root, value);
            update(left, right, root*2+1, value);
        } else if (left > mid) {
			/* [left, right] is fully in root's right subtree */
            passDown(root, value);
            update(left, right, root*2+2, value);
        } else {
            passDown(root, value);
            update(left, mid, root*2+1, value);
            update(mid+1, right, root*2+2, value);
        }
    }

    private Set<Integer> query(final int left, final int right) {
        Set<Integer> result = new HashSet<Integer>();
        query(left, right, 0, result);
        return result;
    }

    private void query(final int left, final int right, final int root, Set<Integer> result) {

        if (left == data[root].left && right == data[root].right && UNINITIALIZED != data[root].value) {
            result.add(data[root].value);
            return;
        }

        int mid = (data[root].left + data[root].right) >> 1;
        if (right <= mid) {
            query(left, right, root*2+1, result);
        } else if (left > mid) {
            query(left, right, root*2+2, result);
        } else {
            query(left, mid, root*2+1, result);
            query(mid+1, right, root*2+2, result);
        }
    }

}
