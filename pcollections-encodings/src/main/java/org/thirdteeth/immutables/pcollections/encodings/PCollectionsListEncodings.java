package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Encoding
class PCollectionsListEncodings<T> {
    @Encoding.Impl
    private List<T> field = TreePVector.empty();

    PCollectionsListEncodings() {

    }

    @Encoding.Builder
    static final class Builder<T>
    {
        private List<T> list = TreePVector.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        void add(final T element) {
            this.list = ((PVector) this.list).plus(element);
        }

        @SafeVarargs
        @Encoding.Naming(standard = Encoding.StandardNaming.ADD)
        @Encoding.Init
        final void addVarArgs(final T... elements) {
            this.list = ((PVector) this.list).plusAll(Arrays.asList(elements));
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            this.list = ((PVector) this.list).plusAll(element);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final List<T> in_list) {
            this.list = TreePVector.from(in_list);
        }

        @Encoding.Init
        @Encoding.Naming(value = "setPVector*")
        void setPVector(final PVector<T> elements) {
            this.list = elements;
        }

        @Encoding.Build
        List<T> build() {
            return this.list;
        }
    }
}
