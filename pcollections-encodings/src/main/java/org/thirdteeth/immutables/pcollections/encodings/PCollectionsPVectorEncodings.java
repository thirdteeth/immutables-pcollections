package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Encoding
class PCollectionsPVectorEncodings<T> {
    @Encoding.Impl
    private PVector<T> field = TreePVector.empty();

    PCollectionsPVectorEncodings() {

    }

    @Encoding.Builder
    static final class Builder<T>
    {
        private PVector<T> list = TreePVector.empty();

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
            this.list = this.list.plusAll(Arrays.asList(elements));
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.ADD_ALL)
        @Encoding.Init
        void addAll(final Collection<T> element) {
            this.list = this.list.plusAll(element);
        }

        @Encoding.Naming(value = "setJavaList*")
        @Encoding.Init
        void setJavaList(final List<T> in_list) {
            this.list = TreePVector.from(in_list);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final PVector<T> elements) {
            this.list = elements;
        }

        @Encoding.Build
        PVector<T> build() {
            return this.list;
        }
    }
}
