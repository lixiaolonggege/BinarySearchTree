import java.util.ArrayList;
public class Test {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        //System.out.println(bst.isEmpty());
       int[] arr={8,7,9,65,12,54,3,4,11,15,12,2};
       for(int i:arr){
           bst.add(i);
       }

        /*System.out.println(bst.size());
        System.out.println(bst.contains2(8));*/
        //bst.midOrder();
        //System.out.println(bst.min());
        /*System.out.println(bst.size());
        bst.midOrder();
        System.out.println("");
        bst.deleteMin();
        System.out.println(bst.size());
        bst.midOrder();
        System.out.println("");
        bst.deleteMin();
        System.out.println(bst.size());
        bst.midOrder();*/
        System.out.println(bst.size());
        bst.midOrder();
        bst.delete(9);
        System.out.println();
        System.out.println(bst.size());
        bst.midOrder();




    }
}
