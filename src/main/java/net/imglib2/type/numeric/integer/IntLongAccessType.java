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
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;

public class IntLongAccessType extends GenericIntLongAccessType< IntLongAccessType >
{

	// this is the constructor if you want it to read from an array
	public IntLongAccessType( final NativeLongAccessImg< ?, ? extends IntLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public IntLongAccessType( final int value )
	{
		super( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public IntLongAccessType( final IntLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public IntLongAccessType()
	{
		super( 0 );
	}

	@Override
	public IntLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new IntLongAccessType( img );
	}

	public int get()
	{
		return getValue();
	}

	public void set( final int b )
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
		return Integer.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return Integer.MIN_VALUE;
	}

	@Override
	public IntLongAccessType createVariable()
	{
		return new IntLongAccessType( 0 );
	}

	@Override
	public IntLongAccessType copy()
	{
		return new IntLongAccessType( getValue() );
	}

	@Override
	public NativeLongAccessImg< IntLongAccessType, ? > createSuitableNativeImg( final NativeLongAccessImgFactory< IntLongAccessType > storageFactory, final long[] dim )
	{
		final NativeLongAccessImg< IntLongAccessType, ? extends IntLongAccess > img = storageFactory.createIntInstance( dim, getEntitiesPerPixel() );
		final IntLongAccessType linkedType = new IntLongAccessType( img );
		img.setLinkedType( linkedType );
		return img;
	}

	private static final NativeLongAccessTypeFactory< IntLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.INT( IntLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< IntLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
