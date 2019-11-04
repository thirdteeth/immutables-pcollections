package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

import java.util.Map;

@Encoding
class PCollectionsPMapEncodings<K, V> {
    // Using a linked variant provides more predictable semantics for serialization
    @Encoding.Impl
    private PMap<K, V> field = HashTreePMap.empty();

    PCollectionsPMapEncodings() {

    }

    @Encoding.Builder
    static final class Builder<K, V> {
        private PMap<K, V> pmap = HashTreePMap.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.PUT)
        @Encoding.Init
        void put(final K key, final V value) {
            this.pmap = this.pmap.plus(key, value);
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final PMap<K, V> elements) {
            if (elements != null && !elements.isEmpty()) {
                this.pmap = elements;
            }
        }

        @Encoding.Naming(value = "setJavaMap*")
        @Encoding.Init
        void setJavaMap(final Map<K, V> in_map) {
            if (in_map != null && !in_map.isEmpty()) {
                this.pmap = HashTreePMap.from(in_map);
            }
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.PUT_ALL)
        @Encoding.Init
        void putAll(final Map<K, V> map) {
            if (map != null && !map.isEmpty()) {
                this.pmap = this.pmap.plusAll(map);
            }
        }

        @Encoding.Build
        PMap<K, V> build()
        {
            return this.pmap;
        }
    }
}
