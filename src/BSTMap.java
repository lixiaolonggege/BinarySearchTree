import java.util.ArrayList;
import java.util.Collections;

public class BSTMap<K extends Comparable<K>,V> {
    private class Node{
        public K k;
        public V v;
        public Node left,right;
        public Node(K k,V v){
            this.k=k;
            this.v=v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }
    }

    private Node root;
    private int size;

    public boolean isEmpty(){return size==0;}
    public int size(){return size;}

    public void add(K k,V v){
        root=add(root,k,v);
    }


    /**
     * 添加元素递归方法
     * @param node
     * @param k
     * @param v
     * @return
     */
    private Node add(Node node,K k,V v){
        if(node==null){
            size++;
            return new Node(k,v);
        }
        if(k.compareTo(node.k)<0){
            node.left=add(node.left,k,v);
        }else if(k.compareTo(node.k)>0){
            node.right=add(node.right,k,v);
        }else {
            node.v=v;
        }
        return node;
    }


    //返回以node为根节点的子树，K为k的节点
    public Node getNode(Node node,K k){
        if(node==null)
            return null;
        if(k.compareTo(node.k)==0)
            return node;
        else if(k.compareTo(node.k)<0){
            return getNode(node.left,k);
        }else {
            return getNode(node.right,k);
        }
    }

    public boolean contains(K k){
        return getNode(root,k)!=null;
    }

    public V get(K k){
        Node node = getNode(root, k);
        return node==null?null:node.v;
    }

    public void set(K k,V v){
        Node node = getNode(root, k);
        if(node==null)
            throw new IllegalArgumentException(k+"doesn't exist");
        node.v=v;
    }

    public void midOrder(){
        midOrder(root);
    }

    /**
     * 中序遍历递归方法
     */
    private void midOrder(Node node){
        if(node==null)
            return;
        midOrder(node.left);
        System.out.print(node.toString()+"\t");
        midOrder(node.right);
    }



    //找出最小的节点
    public Node minNode(){
        return  minNode(root);
    }
    private Node minNode(Node node){
        if(node==null)
            return null;
        while(node.left!=null) node=node.left;
        return node;
    }

    //删除最小节点
    public void deleteMin(){
        deleteMin(root);
    }
    private Node deleteMin(Node node){
        if(node.left==null){
            size--;
            Node right=node.right;
            node.right=null;
            return right;
        }
        node.left=deleteMin(node.left);
        return node;
    }

    /**
     * 删除任意节点
     */
    public V delete(K k){
        Node node = getNode(root, k);
        if(node!=null){
            root=delete(root,k);
            return node.v;
        }
        return null;
    }
    private Node delete(Node node,K k){
        if(node==null)
            return null;
        if (k.compareTo(node.k) == 0) {
            if(node.left==null){
                //待删除结点左子树为空
                Node right=node.right;
                node.right=null;
                size--;
                return right;
            }
            if(node.right==null){ //待删除结点右子树为空
                Node left=node.left;
                node.left=null;
                size--;
                return left;
            }
            //待删除结点左右子树均不为空，则找到比待删除节点大的最小节点，即待删除结点右子树的最小节点
            //用该最小节点顶替待删除节点
            Node rightMin=minNode(node.right);
            rightMin.right = deleteMin(node.right);
            rightMin.left=node.left;
            node.left=node.right=null;
            return rightMin;
        }else if(k.compareTo(node.k)<0){
            node.right=delete(node.left,k);
        }else {
            node.right=delete(node.right,k);
        }
        return node;
    }

    public static void main(String[] args) {
        ArrayList<String> words=new ArrayList<>();
        Collections.addAll(words,"aaa","bbb","ccc","aaa","ddd","eee","aaa","bbb");
        BSTMap<String,Integer> map=new BSTMap<>();
        for(String s:words){
            if(map.contains(s))
                map.set(s,map.get(s)+1);
            else
                map.add(s,1);
        }
        System.out.println("total different words:"+map.size());
        System.out.println("aaa的次数："+map.get("aaa"));
        System.out.println("bbb的次数："+map.get("bbb"));
    }
}
