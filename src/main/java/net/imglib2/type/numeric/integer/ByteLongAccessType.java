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
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;

public class ByteLongAccessType extends GenericByteLongAccessType< ByteLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public ByteLongAccessType( final NativeLongAccessImg< ?, ? extends ByteLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public ByteLongAccessType( final byte value )
	{
		super( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public ByteLongAccessType( final ByteLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public ByteLongAccessType()
	{
		super( ( byte ) 0 );
	}

	@Override
	public NativeLongAccessImg< ByteLongAccessType, ? extends ByteLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< ByteLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< ByteLongAccessType, ? extends ByteLongAccess > container = storageFactory.createByteInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final ByteLongAccessType linkedType = new ByteLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public ByteLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new ByteLongAccessType( img );
	}

	public byte get()
	{
		return getValue();
	}

	public void set( final byte b )
	{
		setValue( b );
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
		set( ( byte ) f );
	}

	@Override
	public void setInteger( final long f )
	{
		set( ( byte ) f );
	}

	@Override
	public void setBigInteger( final BigInteger b )
	{
		set( b.byteValue() );
	}

	@Override
	public double getMaxValue()
	{
		return Byte.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return Byte.MIN_VALUE;
	}

	@Override
	public ByteLongAccessType createVariable()
	{
		return new ByteLongAccessType( ( byte ) 0 );
	}

	@Override
	public ByteLongAccessType copy()
	{
		return new ByteLongAccessType( getValue() );
	}

	private static final NativeLongAccessTypeFactory< ByteLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.BYTE( ByteLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< ByteLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}
}
