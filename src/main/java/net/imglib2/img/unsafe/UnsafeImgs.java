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

package net.imglib2.img.unsafe;

import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.basictypeaccess.array.DoubleArray;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.img.basictypeaccess.array.IntArray;
import net.imglib2.img.basictypeaccess.array.LongArray;
import net.imglib2.img.basictypeaccess.array.ShortArray;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.DoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.FloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningDoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningFloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningIntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningLongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningShortUnsafe;
import net.imglib2.type.Type;
import net.imglib2.type.numeric.ARGBLongAccessType;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.type.numeric.complex.ComplexDoubleLongAccessType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;
import net.imglib2.type.numeric.complex.ComplexFloatLongAccessType;
import net.imglib2.type.numeric.complex.ComplexFloatType;
import net.imglib2.type.numeric.integer.ByteLongAccessType;
import net.imglib2.type.numeric.integer.IntLongAccessType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.LongLongAccessType;
import net.imglib2.type.numeric.integer.LongType;
import net.imglib2.type.numeric.integer.ShortLongAccessType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedLongLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedShortLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.DoubleLongAccessType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.type.numeric.real.FloatLongAccessType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Fraction;

/**
 * <p>
 * Convenience factory methods for creation of {@link UnsafeImg} instances with
 * the most common pixel {@link Type} variants. The collection includes
 * factories to re-use existing primitive type arrays as data. This can be used
 * for in-place access to data from other libraries such as AWT or ImageJ. Keep
 * in mind that this cannot be a complete collection since the number of
 * existing pixel {@link Type}s may be extended.
 * </p>
 *
 * <p>
 * For pixel {@link Type}s T not present in this collection, use the generic
 * {@link UnsafeImgFactory#create(long[], net.imglib2.type.NativeLongAccessType)}, e.g.
 * </p>
 *
 * <pre>
 * img = new ArrayImgFactory&lt; MyType &gt;.create( new long[] { 100, 200 }, new MyType() );
 * </pre>
 *
 * @author Stephan Saalfeld
 */
final public class UnsafeImgs
{
	private UnsafeImgs()
	{}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link UnsignedByteType}, {@link ByteArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< UnsignedByteLongAccessType, OwningByteUnsafe > unsignedBytes( final long... dim )
	{
		return ( UnsafeImg< UnsignedByteLongAccessType, OwningByteUnsafe > ) new UnsafeImgFactory< UnsignedByteLongAccessType >().create( dim, new UnsignedByteLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link UnsignedByteType}, {@link ByteArray}&gt;
	 * reusing a passed byte[] array.
	 */
	final public static UnsafeImg< UnsignedByteLongAccessType, ByteLongAccess > unsignedBytes( final ByteLongAccess access, final long... dim )
	{
		final UnsafeImg< UnsignedByteLongAccessType, ByteLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final UnsignedByteLongAccessType t = new UnsignedByteLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link ByteLongAccessType},
	 * {@link ByteArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< ByteLongAccessType, OwningByteUnsafe > bytes( final long... dim )
	{
		return ( UnsafeImg< ByteLongAccessType, OwningByteUnsafe > ) new UnsafeImgFactory< ByteLongAccessType >().create( dim, new ByteLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link ByteLongAccessType},
	 * {@link ByteArray}&gt; reusing a passed byte[] array.
	 */
	final public static UnsafeImg< ByteLongAccessType, ByteLongAccess > bytes( final ByteLongAccess access, final long... dim )
	{
		final UnsafeImg< ByteLongAccessType, ByteLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final ByteLongAccessType t = new ByteLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link UnsignedShortType},
	 * {@link ShortArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< UnsignedShortLongAccessType, OwningShortUnsafe > unsignedShorts( final long... dim )
	{
		return ( UnsafeImg< UnsignedShortLongAccessType, OwningShortUnsafe > ) new UnsafeImgFactory< UnsignedShortLongAccessType >().create( dim, new UnsignedShortLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link UnsignedShortType},
	 * {@link ShortArray}&gt; reusing a passed short[] array.
	 */
	final public static UnsafeImg< UnsignedShortLongAccessType, ShortLongAccess > unsignedShorts( final ShortLongAccess access, final long... dim )
	{
		final UnsafeImg< UnsignedShortLongAccessType, ShortLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final UnsignedShortLongAccessType t = new UnsignedShortLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link ShortType}, {@link ShortArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< ShortLongAccessType, OwningShortUnsafe > shorts( final long... dim )
	{
		return ( UnsafeImg< ShortLongAccessType, OwningShortUnsafe > ) new UnsafeImgFactory< ShortLongAccessType >().create( dim, new ShortLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link ShortType}, {@link ShortArray}&gt;
	 * reusing a passed short[] array.
	 */
	final public static UnsafeImg< ShortLongAccessType, ShortLongAccess > shorts( final ShortLongAccess access, final long... dim )
	{
		final UnsafeImg< ShortLongAccessType, ShortLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final ShortLongAccessType t = new ShortLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link UnsignedIntType}, {@link IntArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< UnsignedIntLongAccessType, OwningIntUnsafe > unsignedInts( final long... dim )
	{
		return ( UnsafeImg< UnsignedIntLongAccessType, OwningIntUnsafe > ) new UnsafeImgFactory< UnsignedIntLongAccessType >().create( dim, new UnsignedIntLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link UnsignedIntType}, {@link IntArray}&gt;
	 * reusing a passed int[] array.
	 */
	final public static UnsafeImg< UnsignedIntLongAccessType, IntLongAccess > unsignedInts( final IntLongAccess access, final long... dim )
	{
		final UnsafeImg< UnsignedIntLongAccessType, IntLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final UnsignedIntLongAccessType t = new UnsignedIntLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link IntType}, {@link IntArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< IntLongAccessType, IntArray > ints( final long... dim )
	{
		return ( UnsafeImg< IntLongAccessType, IntArray > ) new UnsafeImgFactory< IntLongAccessType >().create( dim, new IntLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link IntType}, {@link IntArray}&gt; reusing a
	 * passed int[] array.
	 */
	final public static UnsafeImg< IntLongAccessType, IntLongAccess > ints( final IntLongAccess access, final long... dim )
	{
		final UnsafeImg< IntLongAccessType, IntLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final IntLongAccessType t = new IntLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link LongType}, {@link LongArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< UnsignedLongLongAccessType, OwningLongUnsafe > unsignedLongs( final long... dim )
	{
		return ( UnsafeImg< UnsignedLongLongAccessType, OwningLongUnsafe > ) new UnsafeImgFactory< UnsignedLongLongAccessType >().create( dim, new UnsignedLongLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link LongType}, {@link LongArray}&gt; reusing
	 * a passed long[] array.
	 */
	final public static UnsafeImg< UnsignedLongLongAccessType, LongLongAccess > unsignedLongs( final LongLongAccess access, final long... dim )
	{
		final UnsafeImg< UnsignedLongLongAccessType, LongLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final UnsignedLongLongAccessType t = new UnsignedLongLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link LongType}, {@link LongArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< LongLongAccessType, OwningLongUnsafe > longs( final long... dim )
	{
		return ( UnsafeImg< LongLongAccessType, OwningLongUnsafe > ) new UnsafeImgFactory< LongLongAccessType >().create( dim, new LongLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link LongType}, {@link LongArray}&gt; reusing
	 * a passed long[] array.
	 */
	final public static UnsafeImg< LongLongAccessType, LongLongAccess > longs( final LongLongAccess access, final long... dim )
	{
		final UnsafeImg< LongLongAccessType, LongLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		final LongLongAccessType t = new LongLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link FloatType}, {@link FloatArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< FloatLongAccessType, OwningFloatUnsafe > floats( final long... dim )
	{
		return ( UnsafeImg< FloatLongAccessType, OwningFloatUnsafe > ) new UnsafeImgFactory< FloatLongAccessType >().create( dim, new FloatLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link FloatType}, {@link FloatArray}&gt;
	 * reusing a passed float[] array.
	 */
	final public static UnsafeImg< FloatLongAccessType, FloatUnsafe > floats( final FloatUnsafe access, final long... dim )
	{
		final UnsafeImg< FloatLongAccessType, FloatUnsafe > img = new UnsafeImg<>( access, dim, new Fraction() );
		final FloatLongAccessType t = new FloatLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link DoubleType}, {@link DoubleArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< DoubleLongAccessType, OwningDoubleUnsafe > doubles( final long... dim )
	{
		return ( UnsafeImg< DoubleLongAccessType, OwningDoubleUnsafe > ) new UnsafeImgFactory< DoubleLongAccessType >().create( dim, new DoubleLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link DoubleType}, {@link DoubleArray}&gt;
	 * reusing a passed double[] array.
	 */
	final public static UnsafeImg< DoubleLongAccessType, DoubleUnsafe > doubles( final DoubleUnsafe access, final long... dim )
	{
		final UnsafeImg< DoubleLongAccessType, DoubleUnsafe > img = new UnsafeImg<>( access, dim, new Fraction() );
		final DoubleLongAccessType t = new DoubleLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link ARGBType}, {@link IntArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< ARGBLongAccessType, OwningIntUnsafe > argbs( final long... dim )
	{
		return ( UnsafeImg< ARGBLongAccessType, OwningIntUnsafe > ) new UnsafeImgFactory< ARGBLongAccessType >().create( dim, new ARGBLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link ARGBType}, {@link IntArray}&gt; reusing
	 * a passed int[] array.
	 */
	final public static UnsafeImg< ARGBLongAccessType, IntLongAccess > argbs( final IntLongAccess access, final long... dim )
	{
		final UnsafeImg< ARGBLongAccessType, IntLongAccess > img = new UnsafeImg<>( access, dim, new Fraction() );
		System.out.println( "DEBUG 1 " + ( img == null ) );
		final ARGBLongAccessType t = new ARGBLongAccessType( img );
		System.out.println( "DEBUG 2 " + ( t == null ) );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link ComplexFloatType},
	 * {@link FloatArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< ComplexFloatLongAccessType, FloatLongAccess > complexFloats( final long... dim )
	{
		return ( UnsafeImg< ComplexFloatLongAccessType, FloatLongAccess > ) new UnsafeImgFactory< ComplexFloatLongAccessType >().create( dim, new ComplexFloatLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link FloatType}, {@link FloatArray}&gt;
	 * reusing a passed float[] array.
	 */
	final public static UnsafeImg< ComplexFloatLongAccessType, FloatLongAccess > complexFloats( final FloatLongAccess access, final long... dim )
	{
		final UnsafeImg< ComplexFloatLongAccessType, FloatLongAccess > img = new UnsafeImg<>( access, dim, new Fraction( 2, 1 ) );
		final ComplexFloatLongAccessType t = new ComplexFloatLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}

	/**
	 * Create an {@link UnsafeImg}&lt;{@link ComplexDoubleType},
	 * {@link DoubleArray}&gt;.
	 */
	@SuppressWarnings( "unchecked" )
	final static public UnsafeImg< ComplexDoubleLongAccessType, DoubleLongAccess > complexDoubles( final long... dim )
	{
		return ( UnsafeImg< ComplexDoubleLongAccessType, DoubleLongAccess > ) new UnsafeImgFactory< ComplexDoubleLongAccessType >().create( dim, new ComplexDoubleLongAccessType() );
	}

	/**
	 * Creates an {@link UnsafeImg}&lt;{@link DoubleType}, {@link DoubleArray}&gt;
	 * reusing a passed double[] array.
	 */
	final public static UnsafeImg< ComplexDoubleLongAccessType, DoubleLongAccess > complexDoubles( final DoubleLongAccess access, final long... dim )
	{
		final UnsafeImg< ComplexDoubleLongAccessType, DoubleLongAccess > img = new UnsafeImg<>( access, dim, new Fraction( 2, 1 ) );
		final ComplexDoubleLongAccessType t = new ComplexDoubleLongAccessType( img );
		img.setLinkedType( t );
		return img;
	}
}
