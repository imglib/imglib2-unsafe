package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
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
			unsignedShort = 0;
		else if ( unsignedShort > 65535 )
			unsignedShort = 65535;

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
			return 1;
		else if ( a < b )
			return -1;
		else
			return 0;
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
}
