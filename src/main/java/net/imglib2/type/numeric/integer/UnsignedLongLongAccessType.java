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
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

/**
 * TODO
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 * @author Albert Cardona
 * @author Mark Hiner
 */
public class UnsignedLongLongAccessType extends GenericLongLongAccessType< UnsignedLongLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public UnsignedLongLongAccessType( final NativeLongAccessImg< ?, ? extends LongLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedLongLongAccessType( final long value )
	{
		super( value );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedLongLongAccessType( final BigInteger value )
	{
		this( value.longValue() );
	}

	// this is the constructor if you want to specify the dataAccess
	public UnsignedLongLongAccessType( final LongLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedLongLongAccessType()
	{
		this( 0 );
	}

	@Override
	public NativeLongAccessImg< UnsignedLongLongAccessType, ? extends LongLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< UnsignedLongLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< UnsignedLongLongAccessType, ? extends LongLongAccess > container = storageFactory.createLongInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final UnsignedLongLongAccessType linkedType = new UnsignedLongLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public UnsignedLongLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new UnsignedLongLongAccessType( img );
	}

	@Override
	public void mul( final float c )
	{
		set( Util.round( ( double ) ( get() * c ) ) );
	}

	@Override
	public void mul( final double c )
	{
		set( Util.round( get() * c ) );
	}

	@Override
	public void add( final UnsignedLongLongAccessType c )
	{
		set( get() + c.get() );
	}

	/**
	 * @see #divide(long, long)
	 */
	@Override
	public void div( final UnsignedLongLongAccessType c )
	{
		set( divide( get(), c.get() ) );
	}

	/**
	 * Unsigned division of {@code d1} by {@code d2}.
	 *
	 * See "Division by Invariant Integers using Multiplication", by Torbjorn
	 * Granlund and Peter L. Montgomery, 1994.
	 * http://gmplib.org/~tege/divcnst-pldi94.pdf
	 *
	 * @throws ArithmeticException
	 *             when c equals zero.
	 */
	static public final long divide( final long d1, final long d2 )
	{
		if ( d2 < 0 )
		{
			// d2 is larger than the maximum signed long value
			if ( -1 == compare( d1, d2 ) )
			{
				// d1 is smaller than d2
				return 0;
			}
			else
			{
				// d1 is larger or equal than d2
				return 1;
			}
		}

		if ( d1 < 0 )
		{
			// Approximate division: exact or one less than the actual value
			final long quotient = ( d1 >>> 1 ) / d2 << 1;
			final long reminder = d1 - quotient * d2;
			return quotient + ( -1 == compare( d2, reminder ) ? 0 : 1 );
		}

		// Exact division, given that both d1 and d2 are smaller than
		// or equal to the maximum signed long value
		return d1 / d2;
	}

	@Override
	public void mul( final UnsignedLongLongAccessType c )
	{
		set( get() * c.get() );
	}

	@Override
	public void sub( final UnsignedLongLongAccessType c )
	{
		set( get() - c.get() );
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode( get() );
	}

	@Override
	public void setOne()
	{
		set( 1 );
	}

	@Override
	public void setZero()
	{
		set( 0 );
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

	@Override
	public String toString()
	{
		return "" + get();
	}

	/**
	 * This method returns the value of the UnsignedLongType as a signed long.
	 * To get the unsigned value, use {@link UnsignedLongType#getBigInteger()} .
	 */
	public long get()
	{
		return dataAccess.getValue( i );
	}

	/**
	 * This method returns the unsigned representation of this UnsignedLongType
	 * as a {@code BigInteger}.
	 */
	@Override
	public BigInteger getBigInteger()
	{
		final BigInteger mask = new BigInteger( "FFFFFFFFFFFFFFFF", 16 );
		return BigInteger.valueOf( get() ).and( mask );
	}

	public void set( final long value )
	{
		dataAccess.setValue( i, value );
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

	public void set( final BigInteger bi )
	{
		set( bi.longValue() );
	}

	/**
	 * The maximum value that can be stored is {@code Math.pow( 2, 64 ) - 1},
	 * which can't be represented with exact precision using a double
	 */
	@Override
	public double getMaxValue()
	{
		return Math.pow( 2, 64 ) - 1;
	} // imprecise

	/**
	 * Returns the true maximum value as a BigInteger, since it cannot be
	 * precisely represented as a {@code double}.
	 */
	public BigInteger getMaxBigIntegerValue()
	{
		return new BigInteger( "+FFFFFFFFFFFFFFFF", 16 );
	}

	@Override
	public double getMinValue()
	{
		return 0;
	}

	@Override
	public int compareTo( final UnsignedLongLongAccessType c )
	{
		return compare( get(), c.get() );
	}

	/**
	 *
	 * @param a
	 * @param b
	 * @return -1 if {@code a < b}, 0 if {@code a == b}, 1 if {@code a > b}.
	 */
	static public final int compare( final long a, final long b )
	{
		if ( a == b )
		{
			return 0;
		}
		else
		{
			boolean test = a < b;
			if ( a < 0 != b < 0 )
			{
				test = !test;
			}
			return test ? -1 : 1;
		}
	}

	@Override
	public UnsignedLongLongAccessType createVariable()
	{
		return new UnsignedLongLongAccessType( 0 );
	}

	@Override
	public UnsignedLongLongAccessType copy()
	{
		return new UnsignedLongLongAccessType( get() );
	}

	private static final NativeLongAccessTypeFactory< UnsignedLongLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.LONG( UnsignedLongLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< UnsignedLongLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
