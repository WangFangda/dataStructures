package tree;

import java.util.Comparator;

/**
 * @author fangda.wang
 */
public class AVLTree {
    public static TreeNode insert(TreeNode root, long val, Comparator<TreeNode> comparator) {
        if (null == root) {
            return new TreeNode(val);
        }

        if (comparator.compare(root, new TreeNode(val)) > 0) {
            root.left = insert(root.left, val, comparator);
        } else {
			/* same value will be inserted as well */
            root.right = insert(root.right, val, comparator);
        }

        root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        root.countLeft = getTreeCounts(root.left);
        root.countRight = getTreeCounts(root.right);

        return reBalance(root);
    }

    public static long searchAndFetchData(final TreeNode root, final int pos) throws NullRootException {
        if (null == root)
            throw new NullRootException();

        if (root.countLeft >= pos) {
            return searchAndFetchData(root.left, pos);
        } else if (root.countLeft + 1 == pos) {
            return root.val;
        } else {
            return searchAndFetchData(root.right, pos - 1 - root.countLeft);
        }
    }

    public static TreeNode delete(TreeNode root, long val) throws NotFoundException {
        if (null == root) {
            throw new NotFoundException();
        }

        if (root.val > val) {
            root.left = delete(root.left, val);
        } else if (root.val < val) {
            root.right = delete(root.right, val);
        } else {
            /* the to-be-deleted TreeNode found */
            if (null == root.left && null == root.right) {
                root = null;
            } else if (null != root.left && null != root.right) {
                /* two children */
                TreeNode newRoot = descendToLeftmost(root.right);
                root.val = newRoot.val;
                root.right = delete(root.right, newRoot.val);
            } else if (null != root.left) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (null == root)
            return root;

        root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        root.countLeft = getTreeCounts(root.left);
        root.countRight = getTreeCounts(root.right);

        return reBalance(root);
    }


    private static int getBalanceFactor(final TreeNode root) {
        if (null == root)
            return 0;
        return getHeight(root.left) - getHeight(root.right);
    }

    private static int getHeight(final TreeNode root) {
        if (null == root)
            return -1;
        else
            return root.ht;
    }

    private static TreeNode rightRotate(TreeNode root) {
        TreeNode newRoot = root.left;
        root.left = newRoot.right;
        root.countLeft = newRoot.countRight;
        newRoot.right = root;
        newRoot.countRight = getTreeCounts(root);

        root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    private static TreeNode leftRotate(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        root.countRight = newRoot.countLeft;
        newRoot.left = root;
        newRoot.countLeft = getTreeCounts(root);

        root.ht = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        newRoot.ht = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }


    private static TreeNode reBalance(TreeNode root) {

        int rootBalanceFactor = getBalanceFactor(root);

        if (rootBalanceFactor > 1) {
            int leftBalanceFactor = getBalanceFactor(root.left);
            if (leftBalanceFactor >= 0) {
				/* case: left-left */
                return rightRotate(root);
            } else {
					/* case: left-right */
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        } else if (rootBalanceFactor < -1) {
            int rightBalanceFactor = getBalanceFactor(root.right);
            if (rightBalanceFactor <= 0) {
				/* case: right-right */
                return leftRotate(root);
            } else {
				/* case: right-left */
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }


    private static int getTreeCounts(final TreeNode root) {
        if (null == root)
            return 0;
        return root.countLeft + root.countRight + 1;
    }

    private static TreeNode descendToLeftmost(final TreeNode root) {
        TreeNode result = root;
        while (null != result.left) {
            result = result.left;
        }
        return result;
    }

    public static class NotFoundException extends Throwable {
    }
    public static class NullRootException extends Throwable {
    }
}
