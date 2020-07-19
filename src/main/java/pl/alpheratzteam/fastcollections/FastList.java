package pl.alpheratzteam.fastcollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author Unix
 * @since 18.07.2020
 */

public class FastList<T>
{
    // private static final int MAXIMUM_CAPACITY = 1 << 30;
    
    private final List<T> list;
    private final String[] searchStrings;
    private final AtomicInteger index = new AtomicInteger();

    public FastList() {
        this(50000);
    }

    public FastList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);

       list = new ArrayList<>();
       searchStrings = new String[initialCapacity];
    }

    public void add(T data) {
        list.add(data);
        searchStrings[index.getAndIncrement()] = ((ListData) data).getKeyData();
    }

    public T get(String keyData) {
        final int index = fastSearch(searchStrings, keyData);
        return index == -1 ? null : list.get(index);
    }
    
    public void remove(String keyData) {
        final int index = fastSearch(searchStrings, keyData);
        if (index == -1) {
            Logger.getLogger(FastList.class.getSimpleName()).warning("Object not found!");
            return;
        }

        list.remove(index);
        searchStrings[index] = null;
    }

    public int size() {
        return list.size();
    }

    public List<T> asList() {
        return list;
    }

    private static int fastSearch(String[] sorted, String key) {
        int first = 0;
        while (first != sorted.length) {
            final String value = sorted[first];
            if (Objects.nonNull(value) && key.compareTo(value) == 0)
                return first; // Found it. return position

            ++first; // Repeat search
        }

        return -1; // Failed to find key
    }
}