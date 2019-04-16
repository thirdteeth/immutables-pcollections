package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.HashTreePSet;
import org.pcollections.PSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Encoding
class PCollectionsPSetEncoding<T> {
    // Using a linked variant provides more predictable semantics for serialization
    @Encoding.Impl
    private PSet<T> field = HashTreePSet.empty();

    PCollectionsPSetEncoding() {

    }

    @Encoding.Copy
    PSet<T> with(final PSet<T> set) {
        return HashTreePSet.from(set);
    }

    @Encoding.Copy
    @Encoding.Naming(value = "with*")
    PSet<T> withCollection(final Collection<T> set) {
        return HashTreePSet.from(set);
    }

    @Encoding.Builder
    static final class Builder<T> {
        private PSet<T> set = HashTreePSet.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(final T element) {
            this.set = this.set.plus(element);
        }

        @SafeVarargs
        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        final void addVarArgs(final T... elements) {
            this.set = this.set.plusAll(Arrays.asList(elements));
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            this.set = this.set.plusAll(element);
        }

        @Encoding.Naming(value = "setJavaSet*")
        @Encoding.Init
        void setJavaSet(final Set<T> in_set) {
            this.set = HashTreePSet.from(in_set);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final PSet<T> elements) {
            this.set = elements;
        }


        @Encoding.Build
        PSet<T> build() {
            return this.set;
        }
    }
}
