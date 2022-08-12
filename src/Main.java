import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int length = 100;
        IntegerList list = new IntegerListImpl();
        for (int i = 0; i < length; i++) {
            list.add((int) (Math.random()*length));
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.contains(10));

//        Integer[] arr1 = list.toArray(),
//                  arr2 = list.toArray(),
//                  arr3 = list.toArray(),
//                  arr4 = list.toArray(),
//                  arr5 = list.toArray();
//
//        long start = System.currentTimeMillis();
//        IntegerListImpl.sortBubble(arr1);
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        IntegerListImpl.sortSelection(arr2);
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        IntegerListImpl.sortInsertion(arr3);
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        IntegerListImpl.quickSort(arr4,0,arr4.length-1);
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        IntegerListImpl.sortInsertion(arr5);
//        System.out.println(System.currentTimeMillis() - start);

    }

}
