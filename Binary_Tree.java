// 定义树型节点

import java.util.Scanner;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class TreeNode<T>{
    T val;
    TreeNode<T> left,right;

    public TreeNode(T val){
        this.val=val;
        left = null;
        right=null;
    }
}

// 定义二叉树
class binaryTree<T>{
    private TreeNode<T> root;
    private int index;
    private List<T> dataList;

    // 构造方法
    public binaryTree(List<T> dataArray){
        this.index=0;
        this.dataList=dataArray;
        this.root=buildTree();
    }

    // 构建树的方法
    public TreeNode<T> buildTree(){
        if (index >= dataList.size()) return null;

        T val = dataList.get(index++);
        if(val==null) return null;

        TreeNode<T> node = new TreeNode<>(val);
        node.left=buildTree();
        node.right=buildTree();

        return node;
    }

    //中序遍历方法无参
    public void inOrderTraversal(){
        inOrderTraversal(root);
        System.out.println();
    }

    //中序遍历方法有参
    public void inOrderTraversal(TreeNode<T> node){
        if(node == null) return;
        inOrderTraversal(node.left);
        System.out.print(node.val.toString() + " ");
        inOrderTraversal(node.right);
    }

    //后序遍历方法无参
    public void postOrderTraversal(){
        postOrderTraversal(root);
        System.out.println();
    }

    //后序遍历方法有参
    public void postOrderTraversal(TreeNode<T> node){
        if(node==null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.val.toString()+" ");
    }

    //计算叶子结点数 空参
    public int countLeaves(){
        return countLeaves(root);
    }

    //计算叶子结点数 实参
    public int countLeaves(TreeNode<T> node){
        if(node==null) return 0;
        if(node.left==null && node.right==null) return 1;

        return countLeaves(node.left)+countLeaves(node.right);
    }

    //计算深度 空参
    public int getDepth(){
        return getDepth(root);
    }

    //计算深度 实参
    public int getDepth(TreeNode<T> node){
        if(node==null) return 0;
        return 1+ Math.max(getDepth(node.left), getDepth(node.right));
    }

    // 非递归的前序遍历
    public void nonRecursivePreOrderTraversal() {
        Deque<TreeNode<T>> stack = new ArrayDeque<>(); 
        TreeNode<T> node = root;
        
        // 遍历的主循环
        while (node != null || !stack.isEmpty()) {
            // 遍历左孩子，同时堆栈保存路径
            while (node != null) {
                System.out.print(node.val.toString() + " ");
                stack.push(node);
                node = node.left;
            }
            // 当左子树遍历完成，回溯并转向右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
        System.out.println();
    }

    // 层次遍历方法 广度优先搜索（BFS）
    public void levelOrderTraversal() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            if (current != null) {
                System.out.print(current.val.toString() + " ");
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println();
        // 获取用户输入，构建树并执行操作
        System.out.println("Enter the node values for tree construction:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // 从用户那里获取字符串输入
        List<Character> treeData = new ArrayList<>();
        
       // 将输入字符串转换为字符列表，#字符代表null
        for (char c : input.toCharArray()) {
            if (c == '#') {
                treeData.add(null); // 将#转换为null
            } else {
                treeData.add(c); // 其他字符直接添加到列表
            }
        }
        
        scanner.close();

        // 创建二叉树实例，并执行各种遍历
        binaryTree<Character> tree = new binaryTree<>(treeData);
        System.out.println("In-order traversal:");
        tree.inOrderTraversal();
        System.out.println("Post-order traversal:");
        tree.postOrderTraversal();

        System.out.println("Non-recursive pre-order traversal:");
        tree.nonRecursivePreOrderTraversal();
        System.out.println("Level-order traversal:");
        tree.levelOrderTraversal();

        System.out.println("Depth of tree: " + tree.getDepth());
        System.out.println("Leaf nodes count: " + tree.countLeaves());



    }


}

/* 运行结果
Enter the node values for tree construction: ABC##DE#G##F###
In-order traversal: C B E G D F A 
Post-order traversal: C G E F D B A 
Non-recursive pre-order traversal: A B C D E G F 
Level-order traversal: A B C D E F G
Depth of tree: 5
Leaf nodes count: 3
 */

