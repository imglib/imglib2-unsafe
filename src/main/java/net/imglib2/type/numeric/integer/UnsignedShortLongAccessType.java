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
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

public class UnsignedShortLongAccessType extends GenericShortLongAccessType< UnsignedShortLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public UnsignedShortLongAccessType( final NativeLongAccessImg< ?, ? extends ShortLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedShortLongAccessType( final int value )
	{
		super( getCodedSignedShort( value ) );
	}

	// this is the constructor if you want to specify the dataAccess
	public UnsignedShortLongAccessType( final ShortLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedShortLongAccessType()
	{
		this( 0 );
	}

	public static short getCodedSignedShortChecked( int unsignedShort )
	{
		if ( unsignedShort < 0 )
		{
			unsignedShort = 0;
		}
		else if ( unsignedShort > 65535 )
		{
			unsignedShort = 65535;
		}

		return getCodedSignedShort( unsignedShort );
	}

	public static short getCodedSignedShort( final int unsignedShort )
	{
		return ( short ) ( unsignedShort & 0xffff );
	}

	public static int getUnsignedShort( final short signedShort )
	{
		return signedShort & 0xffff;
	}

	@Override
	public NativeLongAccessImg< UnsignedShortLongAccessType, ? extends ShortLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< UnsignedShortLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< UnsignedShortLongAccessType, ? extends ShortLongAccess > container = storageFactory.createShortInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final UnsignedShortLongAccessType linkedType = new UnsignedShortLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public UnsignedShortLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new UnsignedShortLongAccessType( img );
	}

	@Override
	public void mul( final float c )
	{
		set( Util.round( get() * c ) );
	}

	@Override
	public void mul( final double c )
	{
		set( ( int ) Util.round( get() * c ) );
	}

	@Override
	public void add( final UnsignedShortLongAccessType c )
	{
		set( get() + c.get() );
	}

	@Override
	public void div( final UnsignedShortLongAccessType c )
	{
		set( get() / c.get() );
	}

	@Override
	public void mul( final UnsignedShortLongAccessType c )
	{
		set( get() * c.get() );
	}

	@Override
	public void sub( final UnsignedShortLongAccessType c )
	{
		set( get() - c.get() );
	}

	@Override
	public void inc()
	{
		set( get() + 1 );
	}

	@Override
	public void dec()
	{
		set( get() - 1 );
	}

	public int get()
	{
		return getUnsignedShort( getValue() );
	}

	public void set( final int f )
	{
		setValue( getCodedSignedShort( f ) );
	}

	@Override
	public int getInteger()
	{
		return get();
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
		set( ( int ) f );
	}

	@Override
	public void setBigInteger( final BigInteger b )
	{
		set( b.intValue() );
	}

	@Override
	public double getMaxValue()
	{
		return -Short.MIN_VALUE + Short.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return 0;
	}

	@Override
	public int hashCode()
	{
		// NB: Use the same hash code as java.lang.Integer#hashCode().
		return get();
	}

	@Override
	public int compareTo( final UnsignedShortLongAccessType c )
	{
		final int a = get();
		final int b = c.get();

		if ( a > b )
		{
			return 1;
		}
		else if ( a < b )
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public UnsignedShortLongAccessType createVariable()
	{
		return new UnsignedShortLongAccessType( 0 );
	}

	@Override
	public UnsignedShortLongAccessType copy()
	{
		return new UnsignedShortLongAccessType( get() );
	}

	@Override
	public String toString()
	{
		return "" + get();
	}

	private static final NativeLongAccessTypeFactory< UnsignedShortLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.SHORT( UnsignedShortLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< UnsignedShortLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
