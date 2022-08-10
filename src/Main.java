import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerList list = new IntegerListImpl(100_000);
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random()*10));
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.contains(10));



//        Integer[] arr1 = arr.toArray(),
//                  arr2 = arr.toArray(),
//                  arr3 = arr.toArray();
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

    }

}
