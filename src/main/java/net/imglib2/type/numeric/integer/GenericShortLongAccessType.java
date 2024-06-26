/*
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2024 Howard Hughes Medical Institute.
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

package net.imglib2.type.numeric.integer;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.basictypeaccess.array.ShortArray;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedShortLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.IntegerType;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

/**
 * Abstract base class for {@link NativeType native} {@link IntegerType}s that
 * encode their value into a 16bit short.
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 */
public abstract class GenericShortLongAccessType< T extends GenericShortLongAccessType< T > > extends AbstractIntegerType< T > implements NativeLongAccessType< T >
{
	long i = 0;

	final protected NativeLongAccessImg< ?, ? extends ShortLongAccess > img;

	// the DataAccess that holds the information
	protected ShortLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public GenericShortLongAccessType( final NativeLongAccessImg< ?, ? extends ShortLongAccess > shortStorage )
	{
		img = shortStorage;
	}

	// this is the constructor if you want it to be a variable
	public GenericShortLongAccessType( final short value )
	{
		img = null;
		dataAccess = new WrappedShortLongAccess<>( new ShortArray( 1 ) );
		setValue( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public GenericShortLongAccessType( final ShortLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public GenericShortLongAccessType()
	{
		this( ( short ) 0 );
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction();
	}

	@Override
	public void updateContainer( final Object c )
	{
		dataAccess = img.update( c );
	}

	protected short getValue()
	{
		return dataAccess.getValue( i );
	}

	protected void setValue( final short f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public void mul( final float c )
	{
		final short a = getValue();
		setValue( ( short ) Util.round( a * c ) );
	}

	@Override
	public void mul( final double c )
	{
		final short a = getValue();
		setValue( ( short ) Util.round( a * c ) );
	}

	@Override
	public void add( final T c )
	{
		final short a = getValue();
		setValue( ( short ) ( a + c.getValue() ) );
	}

	@Override
	public void div( final T c )
	{
		final short a = getValue();
		setValue( ( short ) ( a / c.getValue() ) );
	}

	@Override
	public void mul( final T c )
	{
		final short a = getValue();
		setValue( ( short ) ( a * c.getValue() ) );
	}

	@Override
	public void sub( final T c )
	{
		final short a = getValue();
		setValue( ( byte ) ( a - c.getValue() ) );
	}

	@Override
	public int hashCode()
	{
		return Short.hashCode( getValue() );
	}

	@Override
	public int compareTo( final T c )
	{
		return Short.compare( getValue(), c.getValue() );
	}

	@Override
	public void set( final T c )
	{
		setValue( c.getValue() );
	}

	@Override
	public void setOne()
	{
		setValue( ( short ) 1 );
	}

	@Override
	public void setZero()
	{
		setValue( ( short ) 0 );
	}

	@Override
	public void inc()
	{
		short a = getValue();
		setValue( ++a );
	}

	@Override
	public void dec()
	{
		short a = getValue();
		setValue( --a );
	}

	@Override
	public String toString()
	{
		return "" + getValue();
	}

	@Override
	public void updateIndex( final long index )
	{
		i = index;
	}

	@Override
	public long getIndexLong()
	{
		return i;
	}

	@Override
	public void incIndex()
	{
		++i;
	}

	@Override
	public void incIndex( final long increment )
	{
		i += increment;
	}

	@Override
	public void decIndex()
	{
		--i;
	}

	@Override
	public void decIndex( final long decrement )
	{
		i -= decrement;
	}

	@Override
	public int getBitsPerPixel()
	{
		return 16;
	}

	@Override
	public boolean valueEquals( final T t )
	{
		return getValue() == t.getValue();
	}
}
