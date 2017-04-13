package com.lyj.bst;

/**
 * Created by hadoop on 17/12/16.
 */
public class BSTNode {
    public int data;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(){
        this.data = Integer.MAX_VALUE;
        this.left = null;
        this.right = null;
    }

    public BSTNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }



}
