/**
 * Created by Administrator on 2017/3/2.
 */

// good solution
// leetcode_105
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder);
    }
    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if(preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int edge = 0;
        for (int i = inStart, j = inEnd; i <= j; i++, j--) {
            if (inorder[i] == root.val) {
                edge = i;
                break;
            }
            if (inorder[j] == root.val) {
                edge = j;
                break;
            }
        }
        int leftLen = edge - inStart;
        root.left = helper(preStart + 1, preStart + leftLen, inStart, edge - 1, preorder, inorder);
        root.right = helper(preStart + 1 + leftLen, preEnd, edge + 1, inEnd, preorder, inorder);
        return root;
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode () {
        this.val = 0;
        this.left = null;
        this.right = null;
    }

    TreeNode (int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class ConstructBinaryTree {

    public TreeNode contrust (int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length <= 0)
            return null;
        return contrustCore(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1);
    }

    private TreeNode contrustCore (int[] preOrder, int startPreOrder, int endPreOrder, int[] inOrder, int startInOrder, int endInOrder) {
        int rootValue = preOrder[startPreOrder];
        TreeNode root = new TreeNode(rootValue);

        if (startPreOrder == endPreOrder)
            if (startInOrder == endInOrder && startPreOrder == startInOrder)
                return root;

        int rootInorder = startInOrder;
        while (rootInorder <= endInOrder && inOrder[rootInorder] != rootValue)
            ++rootInorder;

        int leftLength = rootInorder - startInOrder;
        int leftPreOrderEnd = startPreOrder + leftLength;

        if (leftLength > 0)
            root.left = contrustCore(preOrder, startPreOrder+1, leftPreOrderEnd ,inOrder, startInOrder, rootInorder-1);
        if (leftLength < endPreOrder - startPreOrder)
            root.right = contrustCore(preOrder, leftPreOrderEnd+1, endPreOrder, inOrder, rootInorder+1, endInOrder);
        return root;
    }

    public void preOrder (TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder (TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public void afterOrder (TreeNode root) {
        if (root == null) return;
        afterOrder(root.left);
        afterOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};

        ConstructBinaryTree c = new ConstructBinaryTree();

        TreeNode res = c.contrust(preOrder, inOrder);

        c.preOrder(res);
        System.out.println();
        c.inOrder(res);
        System.out.println();
        c.afterOrder(res);
    }
}
