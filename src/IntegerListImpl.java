import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] integerList;
    private int size;

    public IntegerListImpl() {
        integerList = new Integer[16];
    }

//    public IntegerListImpl(int length) {
//        integerList = new Integer[length];
//    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }
    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

    private static boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    private void grow() {
        if (size==integerList.length) {
            Integer[] newList = new Integer[integerList.length*3/2];
            System.arraycopy(integerList,0, newList,0,integerList.length);
            integerList = newList;
        }
    }

    @Override
    public Integer add(Integer item) {
        grow();
        validateItem(item);
        integerList[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        grow();
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            integerList[size++] = item;
            return item;
        }
        System.arraycopy(integerList,index, integerList,index+1,size-index);
        integerList[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        integerList[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int i = indexOf(item);
        if (!contains(item)) {
            throw new ElementNotFoundException();
        }
        System.arraycopy(integerList,i+1, integerList,i,size-i);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        if (index == size) {
            throw new InvalidIndexException();
        }
        System.arraycopy(integerList,index+1, integerList,index,size-index);
        size--;
        return integerList[index];
    }
    @Override
    public boolean contains(Integer item) {
        Integer[] copyOfList = toArray();
        quickSort(copyOfList,0,copyOfList.length-1);
        return binarySearch(copyOfList,item);
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i <size ; i++) {
            if (integerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size-1; i >=0 ; i--) {
            if (integerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return integerList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new ListIsNullException();
        }
        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integerList, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new ItemNullException();
        }
    }

//    private void validateSize() {
//        if (size == integerList.length) {
//            throw new ListIsFullException();
//        }
//    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}
