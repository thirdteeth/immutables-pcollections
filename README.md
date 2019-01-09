immutables-pcollections
===

A set of [Immutables](http://immutables.org) encodings for [PCollections](https://pcollections.org/).


Annotate your types with `@PCollectionsEncodingEnabled`. Alternatively,
you can annotate a `@Style` annotation with `@PCollectionsEncodingEnabled`
and have it apply to any types that are using that style. See
[Styles](http://immutables.github.io/style.html).

Now, any use of [PCollections](https://pcollections.org/) collections in your
abstract value types will magically result in the generated `Builder`
types being augmented with methods to build immutable collections
element-by-element.
