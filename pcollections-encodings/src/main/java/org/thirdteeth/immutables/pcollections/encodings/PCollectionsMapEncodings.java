package org.thirdteeth.immutables.pcollections.encodings;

import org.immutables.encode.Encoding;
import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

import java.util.Map;

@Encoding
class PCollectionsMapEncodings<K, V> {
    // Using a linked variant provides more predictable semantics for serialization
    @Encoding.Impl
    private Map<K, V> field = HashTreePMap.empty();

    PCollectionsMapEncodings() {

    }

    @Encoding.Builder
    static final class Builder<K, V> {
        private Map<K, V> pmap = HashTreePMap.empty();

        Builder() {

        }

        @Encoding.Naming(standard = Encoding.StandardNaming.PUT)
        @Encoding.Init
        void put(final K key, final V value) {
            this.pmap = ((PMap) this.pmap).plus(key, value);
        }

        @Encoding.Init
        @Encoding.Naming(value = "setPMap*")
        void setPmap(final PMap<K, V> elements) {
            this.pmap = elements;
        }

        @Encoding.Init
        @Encoding.Copy
        void set(final Map<K, V> in_map) {
            this.pmap = HashTreePMap.from(in_map);
        }

        @Encoding.Naming(standard = Encoding.StandardNaming.PUT_ALL)
        @Encoding.Init
        void putAll(final Map<K, V> map) {
            this.pmap = ((PMap) this.pmap).plusAll(map);
        }

        @Encoding.Build
        Map<K, V> build()
        {
            return this.pmap;
        }
    }
}
