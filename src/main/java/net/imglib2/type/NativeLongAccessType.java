/*
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2021 Howard Hughes Medical Institute.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.type;

import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.img.NativeImg;
import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.cell.CellImg;
import net.imglib2.util.Fraction;

/**
 * A {@link NativeLongAccessType} is a {@link Type} that that provides access to
 * data stored in Java primitive arrays. To this end, implementations maintain a
 * reference to the current storage array and the index of an element in that
 * array.
 *
 * The {@link NativeLongAccessType} is positioned on the correct storage array
 * and index by accessors ({@link Cursor Cursors} and {@link RandomAccess
 * RandomAccesses} ).
 *
 * <p>
 * The {@link NativeLongAccessType} is the only class that is aware of the
 * actual data type, i.e., which Java primitive type is used to store the data.
 * On the other hand it does not know the storage layout, i.e., how
 * n-dimensional pixel coordinates map to indices in the current array. It also
 * doesn't know whether and how the data is split into multiple chunks. This is
 * determined by the container implementation (e.g., {@link ArrayImg},
 * {@link CellImg}, ...). Separating the storage layout from access and
 * operations on the {@link Type} avoids re-implementation for each container
 * type.
 * </p>
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 * @author Tobias Pietzsch
 */
public interface NativeLongAccessType< T extends NativeLongAccessType< T > > extends Type< T >
{
	/**
	 * The {@link NativeLongAccessType} creates the {@link NativeLongAccessImg}
	 * used for storing image data; based on the given storage strategy and its
	 * size. It basically only decides here which BasicType it uses (float, int,
	 * byte, bit, ...) and how many entities per pixel it needs (e.g. 2 floats
	 * per pixel for a complex number). This enables the separation of
	 * containers and the basic types.
	 *
	 * @param storageFactory
	 *            which storage strategy is used
	 * @param dim
	 *            the dimensions
	 *
	 * @return the instantiated {@link NativeLongAccessImg} where only the
	 *         {@link Type} knows the BasicType it contains.
	 */
	public NativeLongAccessImg< T, ? > createSuitableNativeImg( final NativeLongAccessImgFactory< T > storageFactory, final long[] dim );

	/**
	 * Set the index into the current data array.
	 *
	 * <p>
	 * This is used by accessors (e.g., a {@link Cursor}) to position the
	 * {@link NativeLongAccessType} in the container.
	 *
	 * @param i
	 *            the new array index
	 */
	public void updateIndex( final long i );

	/**
	 * Get the current index into the current data array.
	 *
	 * <p>
	 * This is used by accessors (e.g., a {@link Cursor}) to position the
	 * {@link NativeLongAccessType} in the container.
	 *
	 * @return the current index into the underlying data array
	 */
	public long getIndexLong();

	public void incIndex();

	public void decIndex();

	/**
	 * Increases the index into the current data array by {@code increment}
	 * steps.
	 *
	 * <p>
	 * This is used by accessors (e.g., a {@link Cursor}) to position the
	 * {@link NativeLongAccessType} in the container.
	 *
	 * @param increment
	 *            how many steps
	 */
	public void incIndex( final long increment );

	/**
	 * Decrease the index into the current data array by {@code decrement}
	 * steps.
	 *
	 * <p>
	 * This is used by accessors (e.g., a {@link Cursor}) to position the
	 * {@link NativeLongAccessType} in the container.
	 *
	 * @param decrement
	 *            how many steps
	 */
	public void decIndex( final long decrement );

	/**
	 * Get the number of entities in the storage array required to store one
	 * pixel value. A pixel value may be spread over several or less than one
	 * entity. For example, a complex number may require 2 entries of a float[]
	 * array to store one pixel. Or a 12-bit type might need 12/64th entries of
	 * a long[] array.
	 *
	 * @return the number of storage type entities required to store one pixel
	 *         value.
	 */
	public Fraction getEntitiesPerPixel();

	/**
	 * Creates a new {@link NativeType} which stores in the same physical array.
	 * This is only used internally.
	 *
	 * @return a new {@link NativeType} instance working on the same
	 *         {@link NativeImg}
	 */
	public T duplicateTypeOnSameNativeImg();

	public void updateContainer( Object c );

	public NativeLongAccessTypeFactory< T, ? > getNativeLongAccessTypeFactory();

}
