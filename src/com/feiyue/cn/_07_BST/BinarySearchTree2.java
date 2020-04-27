package com.feiyue.cn._07_BST;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree2<E> {
    private int size;
    private Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        //添加元素前，首先需要检查节点的值是否为空
        elementNotNullCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        //首先找到父节点parent，然后创建新节点node，最后比较parent和node的大小。
        // 如果node小于parent，将parent.left赋值给parent。
        //如果node大于parent，将parent.right赋值给parent。
        //循环比较操作，直到parent.left或parent.right为空，将node赋值给parent.left或parent.right即可。
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

    }

    private int compare(E e1, E e2) {
        return 0;
    }

    public void remove(E element) {

    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    // 递归实现后序遍历
    private void postorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) return;
        // 后序遍历左子树
        postorderTraversal(node.left);
        // 后序遍历右子树
        postorderTraversal(node.right);
        // 打印节点值
        System.out.println(node.element);
    }

    //todo 重要
    private void levelOrderTraversal(Node<E> root) {
        // 根节点为空，直接返回
        if (root == null) return;
        // 创建存储节点的队列
        Queue<Node<E>> queue = new LinkedList<>();
        //将头节点入队列
        queue.offer(root);
        //退出条件，当队列为空
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 获取二叉树的前驱节点（predecessor）
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        //前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //如果node.left == null ，父节点不为空
        //predecessor = node.parent.parent.parent。。。
        if (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return  node.parent;
    }

    //后继节点
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        //后继节点在右子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //如果node.right == null ，父节点不为空
        //predecessor = node.parent.parent.parent。。。
        if (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return  node.parent;
    }


    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) throw new IllegalArgumentException("element must not be null!");
    }



    private static class Node<E> {
        // 存放元素
        E element;
        // 左节点
        Node<E> left;
        // 右节点
        Node<E> right;
        // 父节点
        Node<E> parent;
        // 构造函数
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

}
