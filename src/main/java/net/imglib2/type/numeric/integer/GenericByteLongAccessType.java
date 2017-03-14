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

package net.imglib2.type.numeric.integer;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedByteLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

/**
 * TODO
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 */
public abstract class GenericByteLongAccessType< T extends GenericByteLongAccessType< T > > extends AbstractIntegerType< T > implements NativeLongAccessType< T >
{
	long i = 0;

	final protected NativeLongAccessImg< ?, ? extends ByteLongAccess > img;

	// the DataAccess that holds the information
	protected ByteLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public GenericByteLongAccessType( final NativeLongAccessImg< ?, ? extends ByteLongAccess > byteStorage )
	{
		img = byteStorage;
	}

	// this is the constructor if you want it to be a variable
	public GenericByteLongAccessType( final byte value )
	{
		img = null;
		dataAccess = new WrappedByteLongAccess<>( new ByteArray( 1 ) );
		setValue( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public GenericByteLongAccessType( final ByteLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public GenericByteLongAccessType()
	{
		this( ( byte ) 0 );
	}

	@Override
	public Fraction getEntitiesPerPixel() { return new Fraction(); }

	@Override
	public void updateContainer( final Object c )
	{
		dataAccess = img.update( c );
	}

	protected byte getValue()
	{
		return dataAccess.getValue( i );
	}

	protected void setValue( final byte f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public void mul( final float c )
	{
		final byte a = getValue();
		setValue( ( byte ) Util.round( a * c ) );
	}

	@Override
	public void mul( final double c )
	{
		final byte a = getValue();
		setValue( ( byte ) Util.round( a * c ) );
	}

	@Override
	public void add( final T c )
	{
		final byte a = getValue();
		setValue( ( byte ) ( a + c.getValue() ) );
	}

	@Override
	public void div( final T c )
	{
		final byte a = getValue();
		setValue( ( byte ) ( a / c.getValue() ) );
	}

	@Override
	public void mul( final T c )
	{
		final byte a = getValue();
		setValue( ( byte ) ( a * c.getValue() ) );
	}

	@Override
	public void sub( final T c )
	{
		final byte a = getValue();
		setValue( ( byte ) ( a - c.getValue() ) );
	}

	@Override
	public int hashCode()
	{
		return Byte.hashCode( getValue() );
	}

	@Override
	public int compareTo( final T c )
	{
		return Byte.compare( getValue(), c.getValue() );
	}

	@Override
	public void set( final T c )
	{
		setValue( c.getValue() );
	}

	@Override
	public void setOne()
	{
		setValue( ( byte ) 1 );
	}

	@Override
	public void setZero()
	{
		setValue( ( byte ) 0 );
	}

	@Override
	public void inc()
	{
		byte a = getValue();
		setValue( ++a );
	}

	@Override
	public void dec()
	{
		byte a = getValue();
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
		return Byte.SIZE;
	}

	@Override
	public boolean valueEquals( final T t )
	{
		return getValue() == t.getValue();
	}
}
