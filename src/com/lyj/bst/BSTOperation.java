package com.lyj.bst;

/**
 * Created by hadoop on 17/12/16.
 */
public class BSTOperation {

    //手动构建例子中的二叉树
    public static BSTNode initBST(){
        //Level Root
        BSTNode root = new BSTNode(45);

        //Level 1
        root.left = new BSTNode(12);
        root.right = new BSTNode(53);

        //Level 2
        root.left.left = new BSTNode(3);
        root.left.right = new BSTNode(37);
        root.right.right = new BSTNode(100);

        //Level 3
        root.left.right.left = new BSTNode(24);
        root.right.right.left = new BSTNode(61);

        //Level 4
        root.right.right.left.right = new BSTNode(90);

        //Level 5
        root.right.right.left.right.left = new BSTNode(78);

        return root;
    }

    //模仿Linux文件系统方式输出二叉树
    public static void displayPreOrder(BSTNode root,StringBuilder sb){
        if(root != null){
            System.out.println(sb.toString()+root.data);
            if(root.left != null){
                displayPreOrder(root.left,sb.append("  "));
                sb.delete(sb.length() - 2,sb.length());
            }
            if(root.right != null){
                displayPreOrder(root.right,sb.append("  "));
                sb.delete(sb.length() - 2,sb.length());
            }
        }
    }

    /**
     * 在BST中查找值为key的节点,
     * 但是这种查找不能返回查找失败时最后一个查找的节点,不方便插入操作
     * @param root BST树
     * @param key 要查找的值
     * */
    public static BSTNode searchBST(BSTNode root,int key){
        if(root == null || root.data == key){ //请思考用的为什么是 短路或 root == null
            return root;
        }else if (root.data > key){
            return searchBST(root.left,key);
        }else{
            return searchBST(root.right,key);
        }
    }

    /**
     * 在BST中查找值为key的节点，parent指向查找路径上访问的最后一个节点
     * (适用于插入函数)
     * @param root 指向(查找过程中)当前的节点
     * @param parent 指向(查找过程中)当前节点父节点,初始为null
     * @return 若查找成功则返回该节点;若查找不成功则返回查找路径上的最后一个节点
     * */
    public static BSTNode searchBST(BSTNode root,int key,BSTNode parent){
        if(root == null){
            return parent;
        }else if(root.data == key){
            return root;
        }else if(root.data > key){
            return searchBST(root.left,key,root);
        }else{
            return searchBST(root.right,key,root);
        }
    }

    /**
     * 在BST中插入节点
     * 当二叉树中不存在关键字等于key的节点时，才插入并返回新插入的节点
     * @return 若没有该节点，则返回新建节点;若有该节点，则返回已有的该节点
     * */
    public static BSTNode insertBST(BSTNode root,int key){
        BSTNode curNode = searchBST(root,key,null);
        if(curNode == null){
            return new BSTNode(key);
        }else if (curNode.data != key){
            BSTNode newNode = new BSTNode(key);
            if(curNode.data > key){
                curNode.left = newNode;
            }else{
                curNode.right = newNode;
            }
            return newNode;
        }else { //该key已存在
            return curNode;
        }
    }

    /**
     * 删除BST中的指定节点key
     * 先查找该节点，若没有找到则返回false，找到之后删除该节点并返回true
     * 该方法只是定位该节点，找到节点之后删除该节点的方法在delete()方法中
     * @param parent 指向查找节点的父节点
     * */
    public static boolean deleteBST(BSTNode root,int key,BSTNode parent){
        if(root == null){
            return false;
        }else if (root.data == key){
            if(parent.data > key){
                parent.left = delete(root);
            }else{
                parent.right = delete(root);
            }
            return true;
        }else if (root.data > key){
            return deleteBST(root.left,key,root);
        }else{
            return deleteBST(root.right,key,root);
        }
    }

    /**
     * 由deleteBST方法调用，调用该方法时，已经找到该节点，删除该节点分
     * 为四种情况，最终返回删除后的节点
     * (1). 该节点无左右子树,则把该节点置为null
     * (2)(3). 该节点只有左子树或右子树，则把该节点置为其子树
     * (4). 该节点有左子树和右子树，则把该节点替换为其中序遍历的直接前驱节点，然后该前驱节点只有或没有左子树，适合(1)~(3)的情况，进行处理
     */
    public static BSTNode delete(BSTNode root){
        if (root.left == null && root.right == null){
            return null;
        }else if(root.right == null){
            return root.left;
        }else if (root.left == null){
            return root.right;
        }else {
            BSTNode tmp = root;
            BSTNode tmp_1 = tmp.left;
            while(tmp_1.right != null){
                tmp = tmp_1;
                tmp_1 = tmp_1.right;
            }
            root.data = tmp_1.data;
            if(tmp != root){
                tmp.right = tmp_1.left;
            }else{
                tmp.left = tmp_1.left;
            }
            return root;
        }
    }
}
