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
package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;

public class LongLongAccessType extends GenericLongLongAccessType< LongLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public LongLongAccessType( final NativeLongAccessImg< ?, ? extends LongLongAccess > longStorage )
	{
		super( longStorage );
	}

	// this is the constructor if you want to specify the dataAccess
	public LongLongAccessType( final LongLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public LongLongAccessType( final long value )
	{
		super( value );
	}

	// this is the constructor if you want it to be a variable
	public LongLongAccessType()
	{
		super( 0 );
	}

	@Override
	public NativeLongAccessImg< LongLongAccessType, ? extends LongLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< LongLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< LongLongAccessType, ? extends LongLongAccess > container = storageFactory.createLongInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final LongLongAccessType linkedType = new LongLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public LongLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new LongLongAccessType( img );
	}

	public long get()
	{
		return dataAccess.getValue( i );
	}

	public void set( final long f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public int getInteger()
	{
		return ( int ) get();
	}

	@Override
	public long getIntegerLong()
	{
		return get();
	}

	@Override
	public BigInteger getBigInteger()
	{
		return BigInteger.valueOf( get() );
	}

	@Override
	public void setInteger( final int f )
	{
		set( f );
	}

	@Override
	public void setInteger( final long f )
	{
		set( f );
	}

	@Override
	public void setBigInteger( final BigInteger b )
	{
		set( b.longValue() );
	}

	@Override
	public double getMaxValue()
	{
		return Long.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return Long.MIN_VALUE;
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode( get() );
	}

	@Override
	public int compareTo( final LongLongAccessType c )
	{
		return Long.compare( get(), c.get() );
	}

	@Override
	public LongLongAccessType createVariable()
	{
		return new LongLongAccessType( 0 );
	}

	@Override
	public LongLongAccessType copy()
	{
		return new LongLongAccessType( get() );
	}

	private static final NativeLongAccessTypeFactory< LongLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.LONG( LongLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< LongLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
