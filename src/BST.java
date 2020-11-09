/**
 * BinarySearchTree 二叉搜索树 存储元素E必须是可比较的（E extends Comparable)
 * 此二叉搜索树不存储相同元素
 * @param <E>
 */
public class BST<E extends Comparable<E>>{
    /**
     * 测试main方法.。
     * @param args
     */
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        //System.out.println(bst.isEmpty());
        int[] arr={8,7,9,65,12,54,3,4,11,15,12,2};
        for(int i:arr){
            bst.add(i);
        }
        System.out.println(bst.maxNode());
    }

    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e=e;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }


    private Node root;
    private int size;

    public int size(){
        return size;
    }
    public Node root(){
        return root;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){
        root=add(root,e);
    }

    //添加元素非递归
    public void add2(E e){
        if(root==null){
            root=new Node(e);
            size++;
            return;
        }
        Node cur=root;
        while(true){
            if(e.compareTo(cur.e)<0){
                if(cur.left==null){
                    cur.left=new Node(e);
                    size++;
                    break;
                }
                cur=cur.left;
            }else if(e.compareTo(cur.e)<0){
                if(cur.right==null){
                    cur.right=new Node(e);
                    size++;
                    break;
                }
                cur=cur.right;
            }
        }
    }

    /**
     * 添加元素递归方法
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node,E e){
        if(node==null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }
        return node;
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

    /**
     * 查找元素（是否包含元素e） 递归算法
     */
    public boolean contains(E e) {
        return contains(root,e);
    }
    private boolean contains(Node node,E e){
        if(node==null)
            return false;
        if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else if(e.compareTo(node.e)>0){
            return contains(node.right,e);
        }else {
            return true;
        }
    }

    //非递归算法
    public boolean contains2(E e){
        if(root==null)
            return false;
        Node cur=root;
        while (cur!=null){
            if(e.compareTo(cur.e)==0)
                return true;
            else if(e.compareTo(cur.e)<0){
                cur=cur.left;
            }else {
                cur=cur.right;
            }
        }
        return false;
    }

    //找出最小值
    public E min(){
        return min(root);
    }
    private E min(Node node){
        if(node==null)
            return null;
        while(node.left!=null) node=node.left;
        return node.e;
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

    //找出最大值
    public E max(){
        return max(root);
    }
    private E max(Node node){
        if (node==null)
            return null;
        while (node.right!=null) node=node.right;
        return node.e;

    }

    //找出最大的节点
    public Node maxNode() {
        return maxNode(root);
    }
    private Node maxNode(Node node){
        if (node==null)
            return null;
        while (node.right!=null) node=node.right;
        return node;
    }

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
    public void delete(E e){
        root=delete(root,e);
    }
    private Node delete(Node node,E e){
        if(node==null)
            return null;
        if (e.compareTo(node.e) == 0) {
            if(node.left==null){ //待删除结点左子树为空
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
        }else if(e.compareTo(node.e)<0){
            node.left=delete(node.left,e);
        }else {
            node.right=delete(node.right,e);
        }
        return node;
    }

}
