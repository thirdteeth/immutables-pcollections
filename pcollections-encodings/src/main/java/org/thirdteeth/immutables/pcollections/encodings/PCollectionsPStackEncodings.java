package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Encoding
class PCollectionsPStackEncodings<T> {
    @Encoding.Impl
    private PStack<T> field = ConsPStack.empty();

    PCollectionsPStackEncodings() {

    }

    @Encoding.Copy
    PStack<T> with(final PStack<T> list) {
        return ConsPStack.from(list);
    }

    @Encoding.Copy
    @Encoding.Naming(value = "with*")
    PStack<T> withCollection(final Collection<T> list) {
        return ConsPStack.from(list);
    }

    @Encoding.Builder
    static final class Builder<T>
    {
        private PStack<T> list = ConsPStack.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(final T element) {
            this.list = this.list.plus(element);
        }

        @SafeVarargs
        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        final void addVarArgs(final T... elements) {
            if (elements != null && elements.length > 0) {
                this.list = this.list.plusAll(Arrays.asList(elements));
            }
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            if (element != null && !element.isEmpty()) {
                this.list = this.list.plusAll(element);
            }
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final PStack<T> elements) {
            if (elements != null && !elements.isEmpty()) {
                this.list = elements;
            }
        }

        @Encoding.Naming(value = "setJavaList*")
        @Encoding.Init
        void setJavaList(final List<T> in_list) {
            if (in_list != null && !in_list.isEmpty()) {
                this.list = ConsPStack.from(in_list);
            }
        }

        @Encoding.Build
        PStack<T> build() {
            return this.list;
        }
    }
}
