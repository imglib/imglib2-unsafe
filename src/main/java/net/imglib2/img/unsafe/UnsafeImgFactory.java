/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2016 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
 * John Bogovic, Albert Cardona, Barry DeZonia, Christian Dietz, Jan Funke,
 * Aivar Grislis, Jonathan Hale, Grant Harris, Stefan Helfrich, Mark Hiner,
 * Martin Horn, Steffen Jaensch, Lee Kamentsky, Larry Lindsey, Melissa Linkert,
 * Mark Longair, Brian Northan, Nick Perry, Curtis Rueden, Johannes Schindelin,
 * Jean-Yves Tinevez and Michael Zinsmaier.
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

import net.imglib2.exception.IncompatibleTypeException;
import net.imglib2.img.AbstractImg;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.basictypeaccess.array.ArrayDataAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccessFactory;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningCharUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningDoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningFloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningIntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningLongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningShortUnsafe;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.type.NativeType;
import net.imglib2.util.Fraction;

/**
 *
 *
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 */
public class UnsafeImgFactory< T extends NativeLongAccessType< T > > extends NativeLongAccessImgFactory< T >
{

	public UnsafeImgFactory( final T t )
	{
		super( t );
	}

	@Deprecated
	public UnsafeImgFactory()
	{
		this( null );
	}

	@Override
	public UnsafeImg< T, ? > create( final long[] dim, final T type )
	{
		return ( UnsafeImg< T, ? > ) type.createSuitableNativeImg( this, dim );
	}

	public static long numEntities( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = entitiesPerPixel.mulCeil( AbstractImg.numElements( dimensions ) );
		return numEntities;
	}

	@Override
	public UnsafeImg< T, OwningByteUnsafe > createByteInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningByteUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningCharUnsafe > createCharInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningCharUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningDoubleUnsafe > createDoubleInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningDoubleUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningFloatUnsafe > createFloatInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningFloatUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningIntUnsafe > createIntInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningIntUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningLongUnsafe > createLongInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningLongUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@Override
	public UnsafeImg< T, OwningShortUnsafe > createShortInstance( final long[] dimensions, final Fraction entitiesPerPixel )
	{
		final long numEntities = numEntities( dimensions, entitiesPerPixel );

		return new UnsafeImg<>( new OwningShortUnsafe( numEntities ), dimensions, entitiesPerPixel );
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@Override
	public < S > ImgFactory< S > imgFactory( final S type ) throws IncompatibleTypeException
	{
		if ( NativeType.class.isInstance( type ) ) { return new UnsafeImgFactory(); }
		throw new IncompatibleTypeException( this, type.getClass().getCanonicalName() + " does not implement NativeType." );
	}

	@Override
	public UnsafeImg< T, ? > create( final long... dimensions )
	{
		@SuppressWarnings( { "unchecked", "rawtypes" } )
		final UnsafeImg< T, ? > img = create( dimensions, type(), ( NativeLongAccessTypeFactory ) type().getNativeLongAccessTypeFactory() );
		return img;
	}

	private < A extends ArrayDataAccess< A > > UnsafeImg< T, A > create(
			final long[] dimensions,
			final T type,
			final NativeLongAccessTypeFactory< T, A > typeFactory )
	{
		final Fraction entitiesPerPixel = type.getEntitiesPerPixel();
		final int numEntities = ArrayImgFactory.numEntitiesRangeCheck( dimensions, entitiesPerPixel );
		final A data = UnsafeAccessFactory.get( typeFactory ).createArray( numEntities );
		final UnsafeImg< T, A > img = new UnsafeImg<>( data, dimensions, entitiesPerPixel );
		img.setLinkedType( typeFactory.createLinkedType( img ) );
		return img;
	}
}
