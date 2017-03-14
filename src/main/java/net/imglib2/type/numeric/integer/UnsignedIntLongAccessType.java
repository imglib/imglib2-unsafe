package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

public class UnsignedIntLongAccessType extends GenericIntLongAccessType< UnsignedIntLongAccessType >
{

	// this is the constructor if you want it to read from an array
	public UnsignedIntLongAccessType( final NativeLongAccessImg< ?, ? extends IntLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedIntLongAccessType( final long value )
	{
		super( getCodedSignedInt( value ) );
	}

	// this is the constructor if you want to specify the dataAccess
	public UnsignedIntLongAccessType( final IntLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public UnsignedIntLongAccessType()
	{
		this( 0 );
	}

	public static int getCodedSignedIntChecked( long unsignedInt )
	{
		if ( unsignedInt < 0 )
			unsignedInt = 0;
		else if ( unsignedInt > 0xffffffffL )
			unsignedInt = 0xffffffffL;

		return getCodedSignedInt( unsignedInt );
	}

	public static int getCodedSignedInt( final long unsignedInt )
	{
		return ( int ) ( unsignedInt & 0xffffffff );
	}

	public static long getUnsignedInt( final int signedInt )
	{
		return signedInt & 0xffffffffL;
	}

	@Override
	public NativeLongAccessImg< UnsignedIntLongAccessType, ? extends IntLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< UnsignedIntLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< UnsignedIntLongAccessType, ? extends IntLongAccess > container = storageFactory.createIntInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final UnsignedIntLongAccessType linkedType = new UnsignedIntLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public UnsignedIntLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new UnsignedIntLongAccessType( img );
	}

	@Override
	public void mul( final float c )
	{
		final long a = getUnsignedInt( getValue() );
		setValue( getCodedSignedInt( Util.round( a * c ) ) );
	}

	@Override
	public void mul( final double c )
	{
		final long a = getUnsignedInt( getValue() );
		setValue( getCodedSignedInt( ( int ) Util.round( a * c ) ) );
	}

	@Override
	public void add( final UnsignedIntLongAccessType c )
	{
		set( get() + c.get() );
	}

	@Override
	public void div( final UnsignedIntLongAccessType c )
	{
		set( get() / c.get() );
	}

	@Override
	public void mul( final UnsignedIntLongAccessType c )
	{
		set( get() * c.get() );
	}

	@Override
	public void sub( final UnsignedIntLongAccessType c )
	{
		set( get() - c.get() );
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

	public long get()
	{
		return getUnsignedInt( getValue() );
	}

	public void set( final long f )
	{
		setValue( getCodedSignedInt( f ) );
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
		return 0xffffffffL;
	}

	@Override
	public double getMinValue()
	{
		return 0;
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode( get() );
	}

	@Override
	public int compareTo( final UnsignedIntLongAccessType c )
	{
		return Long.compare( get(), c.get() );
	}

	@Override
	public UnsignedIntLongAccessType createVariable()
	{
		return new UnsignedIntLongAccessType( 0 );
	}

	@Override
	public UnsignedIntLongAccessType copy()
	{
		return new UnsignedIntLongAccessType( get() );
	}

}
