/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/* public */ class Lst<T> implements List<T> {
    private static final String ADD_ERROR_MSG = "Use Lst#append or Lst#prepend";

    /* package */ transient final List<T> storage;

    public Lst() {
        this.storage = Collections.emptyList();
    }

    public Lst(List<T> storage) {
        this.storage = storage;
    }

    public T head() {
        assert size() > 0;
        return storage.get(0);
    }

    public Lst<T> rest() {
        if (isEmpty()) {
            return this;
        } else {
            final int newSize = size() - 1;
            final ArrayList<T> newStorage = new ArrayList<>(newSize);
            newStorage.addAll(storage.subList(1, storage.size()));
            return new Lst<>(newStorage);
        }
    }

    public Lst<T> prepend(T elem) {
        final int newSize = storage.size() + 1;
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.add(elem);
        newStorage.addAll(1, storage);
        return new Lst<>(newStorage);
    }

    public Lst<T> append(T elem) {
        final int newSize = storage.size() + 1;
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.addAll(0, storage);
        newStorage.add(elem);
        return new Lst<>(newStorage);
    }

    public Lst<T> plus(List<T> other) {
        final int newSize = size() + other.size();
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.addAll(storage);
        newStorage.addAll(other);
        return new Lst<>(newStorage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return storage.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new LstIterator();
    }

    @Override
    public Object[] toArray() {
        return storage.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //noinspection SuspiciousToArrayCall
        return storage.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException(ADD_ERROR_MSG);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(storage).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException(ADD_ERROR_MSG);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException(ADD_ERROR_MSG);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        return storage.get(index);
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException(ADD_ERROR_MSG);
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return storage.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return storage.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new LstListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new LstListIterator(index);
    }

    @Override
    public Lst<T> subList(int fromIndex, int toIndex) {
        return new Lst<>(storage.subList(fromIndex, toIndex));
    }

    private final class LstIterator implements Iterator<T> {

        private transient final AtomicInteger i = new AtomicInteger(0);

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return get(i.getAndIncrement());
        }
    }

    private final class LstListIterator implements ListIterator<T> {

        private transient final AtomicInteger i;

        public LstListIterator(int index) {
            assert index >= 0;
            i = new AtomicInteger(index);
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return get(i.getAndIncrement());
        }

        @Override
        public boolean hasPrevious() {
            return i.get() > 0;
        }

        @Override
        public T previous() {
            return get(i.decrementAndGet());
        }

        @Override
        public int nextIndex() {
            return i.get() + 1;
        }

        @Override
        public int previousIndex() {
            return i.get() - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}
