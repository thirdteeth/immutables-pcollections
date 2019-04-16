package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.HashTreePBag;
import org.pcollections.PBag;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Encoding
class PCollectionsPBagEncoding<T> {
    // Using a linked variant provides more predictable semantics for serialization
    @Encoding.Impl
    private PBag<T> field = HashTreePBag.empty();

    PCollectionsPBagEncoding() {

    }

    @Encoding.Copy
    PBag<T> with(final PBag<T> list) {
        return HashTreePBag.from(list);
    }

    @Encoding.Copy
    @Encoding.Naming(value = "with*")
    PBag<T> withCollection(final Collection<T> list) {
        return HashTreePBag.from(list);
    }

    @Encoding.Builder
    static final class Builder<T> {
        private PBag<T> bag = HashTreePBag.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(final T element) {
            this.bag = this.bag.plus(element);
        }

        @SafeVarargs
        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        final void addVarArgs(final T... elements) {
            this.bag = this.bag.plusAll(Arrays.asList(elements));
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            this.bag = this.bag.plusAll(element);
        }

        @Encoding.Naming(value = "setJavaSet*")
        @Encoding.Init
        void setJavaSet(final Set<T> in_set) {
            this.bag = HashTreePBag.from(in_set);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final PBag<T> elements) {
            this.bag = elements;
        }


        @Encoding.Build
        PBag<T> build() {
            return this.bag;
        }
    }
}
