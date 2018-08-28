Knowledge used: Two pointers, Deque (Max Heap)
Similar Question: Q76

NOTE: this is also a two pointer sliding window problem
However, instead of using HashMap, we are using [ArrayDeque](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html)
to implement, roughly speak, a max heap
Deque allows insertion/deletion/peek of element at both ends, and its implementation
in Java, ArrayDeque, provide most operations mentioned above in amortized constant time

Things to keep in mind:
1.
initially when I try to throw away ints that are less than current int from the last,
this is the code I write:
```
while(ad.peekLast() <= curr) {
    ...
}
```
However, there is a one implicitly bug, when the deque has only one element and
you remove it, then the deque is empty and `ad.peekLast` will return `null`
`null` value cannot be compared with `int` so that a `NullPointerException` will
be thrown. So I have to change it to
```
while(!ad.isEmpty() && ad.peekLast() <= curr) {
    ...
}
```
