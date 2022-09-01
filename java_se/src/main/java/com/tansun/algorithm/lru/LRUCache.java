package com.tansun.algorithm.lru;

import java.util.Hashtable;

class DLinkedNode {

    String key;

    int value;

    DLinkedNode pre;// 前指针

    DLinkedNode post;// 后指针

}

/**
 * LRU Cache
 *      最近最少使用
 */

public class LRUCache {

    private Hashtable cache = new Hashtable();

    private int count;

    private int capacity;

    private DLinkedNode head, tail;

    public LRUCache(int capacity) {

        this.count = 0;

        this.capacity = capacity;

        head = new DLinkedNode();

        head.pre = null;// 头结点，前指针为空

        tail = new DLinkedNode();

        tail.post = null;// 尾节点，后指针为空

        head.post = tail;// 头结点的尾指针 指向 尾结点

        tail.pre = head;// 尾结点的前指针 指向 头结点

    }

    public int get(String key) {

        DLinkedNode node = (DLinkedNode)cache.get(key);

        if(node == null){
            // should raise exception here. 应该在这里抛出异常。
            return -1;

        }

        // move the accessed node to the head;将被访问节点移动到头部;

        this.moveToHead(node);

        return node.value;

    }

    public void set(String key, int value) {

        DLinkedNode node = (DLinkedNode)cache.get(key);

        if(node == null){

            DLinkedNode newNode = new DLinkedNode();

            newNode.key = key;

            newNode.value = value;

            this.cache.put(key, newNode);

            this.addNode(newNode);

            ++count;

            if(count > capacity){

                // pop the tail

                DLinkedNode tail = this.popTail();

                this.cache.remove(tail.key);

                --count;

            }

        }else{

            // update the value.

            node.value = value;

            this.moveToHead(node);

        }

    }

    /**

     * Always add the new node right after head;

     */

    // 总是在头部之后添加新节点;
    private void addNode(DLinkedNode node){

        node.pre = head;

        node.post = head.post;

        head.post.pre = node;

        head.post = node;

    }

    /**

     * Remove an existing node from the linked list.

     */

    // 从链表中删除一个现有的节点。
    private void removeNode(DLinkedNode node){

        DLinkedNode pre = node.pre;

        DLinkedNode post = node.post;

        pre.post = post;

        post.pre = pre;

    }

    /**

     * Move certain node in between to the head.

     */

    // 将中间的某些节点移动到头部。
    private void moveToHead(DLinkedNode node){

        this.removeNode(node);

        this.addNode(node);

    }

    // pop the current tail.弹出当前的尾节点

    private DLinkedNode popTail(){

        DLinkedNode res = tail.pre;

        this.removeNode(res);

        return res;

    }

}