package LaberonLSDZ2;

import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private static final int INIT_SIZE = 15;

    private final Integer[] data;
    private int capacity;

    public IntegerListImpl() {
        data = new Integer[INIT_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Список должен быть положительным");
        }
        data = new Integer[i];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (capacity >= data.length) {
            throw new IllegalArgumentException("Список полный");
        }
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index, false);
        System.arraycopy(data, index, data, index + 1, capacity - index);
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkIndex(index, true);
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        int removeIndex = indexOf(item);
        if (removeIndex == -1) {
            throw new IllegalArgumentException("Не найдено");
        }
        return remove(removeIndex);
    }

    @Override
    public Integer remove(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index, true);
        Integer remove = data[index];
        System.arraycopy(data, index + 1, data, index, capacity - 1 - index);
        data[--capacity] = null;
        return remove;
    }

    @Override
    public boolean contains(Integer item) {
        checkNotNull(item);
        Integer[] arrayForSearch = toArray();
        sortInsertion(arrayForSearch);
        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkNotNull(item);
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNotNull(item);
        int index = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        checkNonNegativeIndex(index);
        checkIndex(index, true);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!data[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    private void sortInsertion(Integer[] arr) {
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

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(data, 0, result, 0, capacity);
        return result;
    }

    private void checkNotNull(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Не должно быть NULL");
        }
    }

    private void checkNonNegativeIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Не должно быть отрицательным");
        }
    }

    private void checkIndex(int index, boolean equality) {
        boolean expression = equality ? index >= capacity : index > capacity;
        if (expression) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер" + capacity);
        }
    }
}
