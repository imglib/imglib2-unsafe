/*-
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
package net.imglib2.type.numeric.real;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedFloatLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;

public class FloatLongAccessType extends AbstractRealType< FloatLongAccessType > implements NativeLongAccessType< FloatLongAccessType >
{
	private long i = 0;

	final protected NativeLongAccessImg< ?, ? extends FloatLongAccess > img;

	// the DataAccess that holds the information
	protected FloatLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public FloatLongAccessType( final NativeLongAccessImg< ?, ? extends FloatLongAccess > doubleStorage )
	{
		img = doubleStorage;
	}

	// this is the constructor if you want it to be a variable
	public FloatLongAccessType( final double value )
	{
		img = null;
		dataAccess = new WrappedFloatLongAccess<>( new FloatArray( 1 ) );
		set( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public FloatLongAccessType( final FloatLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public FloatLongAccessType()
	{
		this( 0 );
	}

	@Override
	public NativeLongAccessImg< FloatLongAccessType, ? extends FloatLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< FloatLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< FloatLongAccessType, ? extends FloatLongAccess > container = storageFactory.createFloatInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final FloatLongAccessType linkedType = new FloatLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public void updateContainer( final Object c )
	{
		dataAccess = img.update( c );
	}

	@Override
	public FloatLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new FloatLongAccessType( img );
	}

	public float get()
	{
		return dataAccess.getValue( i );
	}

	public void set( final float f )
	{
		dataAccess.setValue( i, f );
	}

	public void set( final double f )
	{
		set( ( float ) f );
	}

	@Override
	public float getRealFloat()
	{
		return get();
	}

	@Override
	public double getRealDouble()
	{
		return get();
	}

	@Override
	public void setReal( final float real )
	{
		set( real );
	}

	@Override
	public void setReal( final double real )
	{
		set( real );
	}

	@Override
	public double getMaxValue()
	{
		return Float.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return -Float.MAX_VALUE;
	}

	@Override
	public double getMinIncrement()
	{
		return Float.MIN_VALUE;
	}

	@Override
	public FloatLongAccessType createVariable()
	{
		return new FloatLongAccessType( 0 );
	}

	@Override
	public FloatLongAccessType copy()
	{
		return new FloatLongAccessType( get() );
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction();
	}

	@Override
	public void incIndex()
	{
		++i;
	}

	@Override
	public void decIndex()
	{
		--i;
	}

	@Override
	public int getBitsPerPixel()
	{
		return Float.SIZE;
	}

	@Override
	public boolean valueEquals( final FloatLongAccessType t )
	{
		return get() == t.get();
	}

	@Override
	public void updateIndex( final long k )
	{
		this.i = k;
	}

	@Override
	public long getIndexLong()
	{
		return i;
	}

	@Override
	public void incIndex( final long increment )
	{
		i += increment;
	}

	@Override
	public void decIndex( final long decrement )
	{
		i -= decrement;
	}

	private static final NativeLongAccessTypeFactory< FloatLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.FLOAT( FloatLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< FloatLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
