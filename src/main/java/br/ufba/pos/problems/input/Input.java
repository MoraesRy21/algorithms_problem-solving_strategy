package br.ufba.pos.problems.input;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * An input the hold a List of <T> as Iterable.
 *
 * @param <T> type of input
 */
public class Input<T> implements Iterable<T> {

    private List<T> list;

    protected Input(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
