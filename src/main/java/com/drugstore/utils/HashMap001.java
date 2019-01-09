package com.drugstore.utils;

/**
 * @description: 通过键值对存取值,采用数组加链表的方式
 * @author: zhanglx
 * @date: 2019/1/9 10:09
 */
public class HashMap001 {

    /**
    * 默认初始容量大小为16
    */
    private static final int INITIAL_CAPACITY=16;

    private int capacity;

    /**
    * 阈值，当数组实际大小size 等于容量*阈值时，数组扩容，大小为先前的两倍
    */
    private double threshold=0.75;

    /**
    * 数组实际大小
    */
    private int size=0;

   // private int hashcode;

    private Node[] nodes;

    private int hash(int hashcode){
        return hashcode ^ hashcode<<16;
    }

    private int index(int hash){
        return hash % capacity;
    }

    /**
    * 默认构造方法，初始容量为 INITIAL_CAPACITY
    */
    public HashMap001(){
        capacity=INITIAL_CAPACITY;
        init(capacity);
    }

    public HashMap001(int capacity){
        this.capacity=capacity;
        init(capacity);
    }

    public HashMap001(int capacity, int threshold){
        this.capacity=capacity;
        this.threshold=threshold;
        init(capacity);
    }

    private void init(int capacity){
        nodes=new Node[capacity];
    }

    public void put(Object key,Object value){
        //获取hash
        int hash = hash(key.hashCode());

        //获取索引值
        int index = index(hash);

        //检查是否已有该index，若没有，则直接设置key value
        if(nodes[index]!=null){
            //索引值相同时，key可能相同
            if(nodes[index].getKey().equals(key)){
                nodes[index].setValue(value);
            }else{
                Node node=nodes[index];
                while(node.next!=null){
                    node=node.next;
                }
                node.next=new Node(key,value);
            }
            return;
        }

        nodes[index].setKey(key);
        nodes[index].setValue(value);
        size++;
        //todo
        //对数组实际大小进行判断 是否需要进行扩容

    }

    public Object get(Object key){
        return null;
    }

    public boolean containsKey(Object key){
        for(int i=0;i<size;i++){
            if(nodes[i].key.equals(key)){
                return true;
            }
        }
        return false;
    }


    class Node{

        private Object key;

        private Object value;

        private Node next;

        private int hashcode;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getHashcode() {
            return hashcode;
        }

        public void setHashcode(int hashcode) {
            this.hashcode = hashcode;
        }
    }
}
