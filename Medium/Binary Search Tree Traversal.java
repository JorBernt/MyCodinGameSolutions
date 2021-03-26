import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    static List<Integer> pO = new ArrayList<>();
    static List<Integer> iO = new ArrayList<>();
    static List<Integer> posO = new ArrayList<>();
    static List<Integer> lO = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            int vi = in.nextInt();
            if(i==0) {
                root = new TreeNode(vi);
                continue;
            }
            TreeNode node = new TreeNode(vi);
            insert(root, node);
        }
        preOrder(root);
        inOrder(root);
        postOrder(root);
        levelOrder(root);
        System.out.println(pO.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println(iO.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println(posO.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println(lO.stream().map(Object::toString).collect(Collectors.joining(" ")));

    }

    static void preOrder(TreeNode node) {
        if(node!=null) {
            pO.add(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    static void inOrder(TreeNode node) {
        if(node != null) {
            inOrder(node.left);
            iO.add(node.val);
            inOrder(node.right);
        }
    }

    static void postOrder(TreeNode node) {
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            posO.add(node.val);
        }
    }

    static void levelOrder(TreeNode node) {
        Queue<TreeNode> toVisit = new LinkedList<>();
        toVisit.add(node);
        while(!toVisit.isEmpty()) {
            TreeNode polled = toVisit.poll();
            lO.add(polled.val);
            if(polled.left!=null) {
                toVisit.add(polled.left);
            }
            if(polled.right!=null) {
                toVisit.add(polled.right);
            }
        }
    }

    static void insert(TreeNode root, TreeNode node) {
        if(node.val < root.val) {
            if(root.left == null) {
                root.left = node;
                return;
            }
            insert(root.left, node);
        }
        else {
            if(root.right == null) {
                root.right = node;
                return;
            }
            insert(root.right, node);
        }
    }
}
