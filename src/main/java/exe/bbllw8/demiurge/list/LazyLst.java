/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.list;

import exe.bbllw8.demiurge.either.Either;
import exe.bbllw8.demiurge.either.Left;
import exe.bbllw8.demiurge.either.Right;
import java.util.ArrayList;
import java.util.List;

/* public */ final class LazyLst<T> extends Lst<T> {

    private transient final Either<ItemFactory<T>, ItemTransformer<T>> producer;
    private transient final int limit;

    public LazyLst(ItemFactory<T> factory) {
        this(new ArrayList<>(), -1, new Left<>(factory));
    }

    public LazyLst(ItemTransformer<T> transformer) {
        this(new ArrayList<>(), -1, new Right<>(transformer));
    }

    public LazyLst(int limit, ItemFactory<T> factory) {
        this(new ArrayList<>(limit), limit, new Left<>(factory));
    }

    public LazyLst(int limit, ItemTransformer<T> transformer) {
        this(new ArrayList<>(limit), limit, new Right<>(transformer));
    }

    private LazyLst(List<T> storage, int limit,
            Either<ItemFactory<T>, ItemTransformer<T>> producer) {
        super(storage);
        this.producer = producer;
        this.limit = limit;
    }

    @Override
    public Lst<T> rest() {
        if (isEmpty()) {
            return producer.fold(f -> new LazyLst<>(limit, f),
                    t -> new LazyLst<>(limit, t));
        } else {
            final int newSize = size() - 1;
            final ArrayList<T> newStorage = new ArrayList<>(newSize);
            newStorage.addAll(storage.subList(1, storage.size()));
            return new LazyLst<>(newStorage, limit, producer);
        }
    }

    @Override
    public Lst<T> prepend(T elem) {
        final int newSize = storage.size() + 1;
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.add(elem);
        newStorage.addAll(1, storage);
        final int newLimit = Math.max(newSize, limit);
        return new LazyLst<>(newStorage, newLimit, producer);
    }

    @Override
    public Lst<T> append(T elem) {
        final int newSize = storage.size() + 1;
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.addAll(0, storage);
        newStorage.add(elem);
        final int newLimit = Math.max(newSize, limit);
        return new LazyLst<>(newStorage, newLimit, producer);
    }

    @Override
    public Lst<T> plus(List<T> other) {
        final int newSize = size() + other.size();
        final ArrayList<T> newStorage = new ArrayList<>(newSize);
        newStorage.addAll(storage);
        newStorage.addAll(other);
        final int newLimit = Math.max(newSize, limit);
        return new LazyLst<T>(newStorage, newLimit, producer);
    }

    @Override
    public T get(int index) {
        final int size = storage.size();
        if (index < size) {
            final T obj = storage.get(index);
            if (obj == null) {
                // Create
                final T newObj = produce(index);
                storage.set(index, newObj);
                return newObj;
            } else {
                // Already created
                return obj;
            }
        } else if (limit < 0 || index < limit) {
            for (int i = 0; i < index; i++) {
                // Grow the list
                storage.add(null);
            }
            // Create
            final T newObj = produce(index);
            storage.add(newObj);
            return newObj;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Index (%d) must be < limit (%d)", index, limit));
        }
    }

    private T produce(int index) {
        assert index >= 0 && (limit < 0 || index < limit);
        return producer.fold(f -> f.create(index), t -> {
            if (index == 0) {
                return t.first();
            } else {
                return t.transform(get(index - 1));
            }
        });
    }

    public interface ItemFactory<T> {

        T create(int index);
    }

    public interface ItemTransformer<T> {

        T first();

        T transform(T previous);
    }
}
