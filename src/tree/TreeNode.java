package tree;

import java.util.Comparator;

/**
 * @author fangda.wang
 */
public class TreeNode {

    public static final Comparator<TreeNode> comparator = new Comparator<TreeNode>() {
        @Override
        public int compare(TreeNode o1, TreeNode o2) {
            return o1.val - o2.val > 0 ? 1 : -1;
        }
    };

    public long val;
    public int ht; // if root is null, ht should be -1; leaf node's ht should be 0
    public TreeNode left;
    public TreeNode right;
    public int countLeft;
    public int countRight;

    public TreeNode (long val) {
        this.val = val;
    }

}
