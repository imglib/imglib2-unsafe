/*-
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2023 Howard Hughes Medical Institute.
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
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

public class UnsignedByteLongAccessType extends GenericByteLongAccessType< UnsignedByteLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public UnsignedByteLongAccessType( final NativeLongAccessImg< ?, ? extends ByteLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedByteLongAccessType( final int value )
	{
		super( getCodedSignedByte( value ) );
	}

	// this is the constructor if you want to specify the dataAccess
	public UnsignedByteLongAccessType( final ByteLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedByteLongAccessType()
	{
		this( 0 );
	}

	public static byte getCodedSignedByteChecked( int unsignedByte )
	{
		if ( unsignedByte < 0 )
		{
			unsignedByte = 0;
		}
		else if ( unsignedByte > 255 )
		{
			unsignedByte = 255;
		}

		return getCodedSignedByte( unsignedByte );
	}

	public static byte getCodedSignedByte( final int unsignedByte )
	{
		return ( byte ) ( unsignedByte & 0xff );
	}

	public static int getUnsignedByte( final byte signedByte )
	{
		return signedByte & 0xff;
	}

	@Override
	public NativeLongAccessImg< UnsignedByteLongAccessType, ? extends ByteLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< UnsignedByteLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< UnsignedByteLongAccessType, ? extends ByteLongAccess > container = storageFactory.createByteInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final UnsignedByteLongAccessType linkedType = new UnsignedByteLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public UnsignedByteLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new UnsignedByteLongAccessType( img );
	}

	@Override
	public void mul( final float c )
	{

		final int a = getUnsignedByte( getValue() );
		setValue( getCodedSignedByte( Util.round( a * c ) ) );
	}

	@Override
	public void mul( final double c )
	{
		final int a = getUnsignedByte( getValue() );
		setValue( getCodedSignedByte( ( int ) Util.round( a * c ) ) );
	}

	@Override
	public void add( final UnsignedByteLongAccessType c )
	{
		set( get() + c.get() );
	}

	@Override
	public void div( final UnsignedByteLongAccessType c )
	{
		set( get() / c.get() );
	}

	@Override
	public void mul( final UnsignedByteLongAccessType c )
	{
		set( get() * c.get() );
	}

	@Override
	public void sub( final UnsignedByteLongAccessType c )
	{
		set( get() - c.get() );
	}

	public int get()
	{
		return getUnsignedByte( getValue() );
	}

	public void set( final int f )
	{
		setValue( getCodedSignedByte( f ) );
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
		return -Byte.MIN_VALUE + Byte.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return 0;
	}

	@Override
	public int hashCode()
	{
		return Byte.hashCode( getValue() );
	}

	@Override
	public int compareTo( final UnsignedByteLongAccessType c )
	{
		return Byte.compare( getValue(), c.getValue() );
	}

	@Override
	public UnsignedByteLongAccessType createVariable()
	{
		return new UnsignedByteLongAccessType( 0 );
	}

	@Override
	public UnsignedByteLongAccessType copy()
	{
		return new UnsignedByteLongAccessType( get() );
	}

	@Override
	public String toString()
	{
		return "" + get();
	}

	private static final NativeLongAccessTypeFactory< UnsignedByteLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.BYTE( UnsignedByteLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< UnsignedByteLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
