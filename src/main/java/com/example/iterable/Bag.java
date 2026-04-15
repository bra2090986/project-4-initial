package com.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<E> implements Container<E> {
    private final List<E> items;

    public Bag() {
        this.items = new ArrayList<>();
    }

    @Override
    public void add(E item) {
        items.add(item);
    }

    @Override
    public boolean remove(E item) {
        return items.remove(item);
    }

    @Override
    public boolean contains(E item) {
        return items.contains(item);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        items.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return items.spliterator();
    }
}
