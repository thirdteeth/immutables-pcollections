package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.HashTreePSet;
import org.pcollections.PSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Encoding
class PCollectionsSetEncoding<T> {
    // Using a linked variant provides more predictable semantics for serialization
    @Encoding.Impl
    private Set<T> field = HashTreePSet.empty();

    PCollectionsSetEncoding() {

    }

    @Encoding.Builder
    static final class Builder<T> {
        private Set<T> set = HashTreePSet.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(final T element) {
            this.set = ((PSet) this.set).plus(element);
        }

        @SafeVarargs
        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        final void addVarArgs(final T... elements) {
            this.set = ((PSet) this.set).plusAll(Arrays.asList(elements));
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            this.set = ((PSet) this.set).plusAll(element);
        }

        @Encoding.Copy
        @Encoding.Init
        void set(final Set<T> in_set) {
            this.set = HashTreePSet.from(in_set);
        }

        @Encoding.Init
        @Encoding.Naming(value = "setPSet*")
        void setPSet(final PSet<T> elements) {
            this.set = elements;
        }


        @Encoding.Build
        Set<T> build() {
            return this.set;
        }
    }
}
