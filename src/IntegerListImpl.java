import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private final Integer[] IntegerList;
    private int size;

    public IntegerListImpl() {
        IntegerList = new Integer[10];
    }

    public IntegerListImpl(int length) {
        IntegerList = new Integer[length];
    }

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

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        IntegerList[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            IntegerList[size++] = item;
            return item;
        }
        System.arraycopy(IntegerList,index,IntegerList,index+1,size-index);
        IntegerList[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        IntegerList[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int i = indexOf(item);
        if (!contains(item)) {
            throw new ElementNotFoundException();
        }
        System.arraycopy(IntegerList,i+1,IntegerList,i,size-i);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        if (index == size) {
            throw new InvalidIndexException();
        }
        System.arraycopy(IntegerList,index+1,IntegerList,index,size-index);
        size--;
        return IntegerList[index];
    }
    @Override
    public boolean contains(Integer item) {
        Integer[] copyOfList = toArray();
        sortSelection(copyOfList);
        return binarySearch(copyOfList,item);
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i <size ; i++) {
            if (IntegerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size-1; i >=0 ; i--) {
            if (IntegerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return IntegerList[index];
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
        return Arrays.copyOf(IntegerList, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new ItemNullException();
        }
    }

    private void validateSize() {
        if (size == IntegerList.length) {
            throw new ListIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}
