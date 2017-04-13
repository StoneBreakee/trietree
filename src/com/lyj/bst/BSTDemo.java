package com.lyj.bst;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hadoop on 17/12/16.
 */
public class BSTDemo {

    public static void main(String[] args){
        BSTNode root = BSTOperation.initBST();

        //打印BST
        BSTOperation.displayPreOrder(root,new StringBuilder());

        System.out.println("BST 查找");
        BSTNode tmp = BSTOperation.searchBST(root,60);
        String result = tmp == null?"Not Found":tmp.data+"";
        System.out.println(result);
        tmp = BSTOperation.searchBST(root,78);
        result = tmp == null?"Not Found":tmp.data+"";
        System.out.println(result);

        System.out.println("\nBST 查找 version 2.0");
        int key = 79;
        BSTNode resultNode  = BSTOperation.searchBST(root,key,null);
        String info = "";
        if(resultNode == null || resultNode.data != key){
            info = "查找失败";
            if(resultNode == null){
                info += "树为空";
            }else{
                info += "上一个节点为:"+resultNode.data;
            }
        }else{
            info = "查找成功"+resultNode.data;
        }
        System.out.println(info);

        System.out.println("\nBST 插入节点");
        BSTOperation.insertBST(root,79);
        BSTOperation.insertBST(root,79);
        BSTOperation.insertBST(root,58);
        BSTOperation.insertBST(root,57);
        BSTOperation.displayPreOrder(root,new StringBuilder(""));

        //从空树出发构建一个二叉排序树
        System.out.println("使用insertBST方法创建一颗新树");
        int[] eles = new int[]{7,3,5,9,1,4,0,2,8,6,-1};
        BSTNode newRoot = new BSTNode(eles[0]);
        for (int i: Arrays.copyOfRange(eles,1,eles.length) ) {
            BSTOperation.insertBST(newRoot,i);
        }
        // 可见BST并不能保持平衡，极端情况下会变成线性的树
        BSTOperation.displayPreOrder(newRoot,new StringBuilder(""));

        System.out.println("使用deleteBST删除节点");
        BSTOperation.deleteBST(newRoot,-1,null);
        BSTOperation.displayPreOrder(newRoot,new StringBuilder(""));
    }

}
