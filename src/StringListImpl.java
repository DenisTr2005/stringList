import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] stringList;
    private int size;

    public StringListImpl() {
        stringList = new String[10];
    }

    public StringListImpl(int length) {
        stringList = new String[length];
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        stringList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            stringList[size++] = item;
            return item;
        }
        System.arraycopy(stringList,index,stringList,index+1,size-index);
        stringList[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        stringList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int i = indexOf(item);
        if (!contains(item)) {
            throw new ElementNotFoundException();
        }
        System.arraycopy(stringList,i+1,stringList,i,size-i);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        if (index == size) {
            throw new InvalidIndexException();
        }
        System.arraycopy(stringList,index+1,stringList,index,size-index);
        size--;
        return stringList[index];
    }
    @Override
    public boolean contains(String item) {
        return indexOf(item)!=-1;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i <size ; i++) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size-1; i >=0 ; i--) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(stringList, size);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new ItemNullException();
        }
    }

    private void validateSize() {
        if (size == stringList.length) {
            throw new ListIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}
