[![](https://travis-ci.org/imglib/imglib2-unsafe.svg?branch=master)](https://travis-ci.org/imglib/imglib2-unsafe)

# imglib2-unsafe

The `imglib2` `ArrayImg` class is limited to storage that holds a maximum of `Integer.MAX_VALUE` elements.
`imglib2-unsafe` is an effort to create `ArrayImg` like `Img` objects that can store elements beyond that number in a single contiguous chunk of memory.
In addition to that, arbitrary memory pointers/addresses can serve as data backend through `sun.misc.Unsafe`.
It is the callers responsibility to ensure validity of the accessed memory.

This project is in an early stage of development and the interface is likely to change.
Many of the classes, with the exception of any that use `sun.misc.Unsafe`, in this package could be considered to be added to `imglib2` core.

An example use case for this is [imglib2-imglyb](https://github.com/hanslovsky/imglib2-imglyb), a python-to-ImgLib2 bridge that combines Java and Python through pyjnius (jni).
