package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.util.Fraction;

public class ShortLongAccessType extends GenericShortLongAccessType< ShortLongAccessType >
{
	// this is the constructor if you want it to read from an array
	public ShortLongAccessType( final NativeLongAccessImg< ?, ? extends ShortLongAccess > img )
	{
		super( img );
	}

	// this is the constructor if you want it to be a variable
	public ShortLongAccessType( final short value )
	{
		super( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public ShortLongAccessType( final ShortLongAccess access )
	{
		super( access );
	}

	// this is the constructor if you want it to be a variable
	public ShortLongAccessType()
	{
		this( ( short ) 0 );
	}

	@Override
	public NativeLongAccessImg< ShortLongAccessType, ? extends ShortLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< ShortLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< ShortLongAccessType, ? extends ShortLongAccess > container = storageFactory.createShortInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final ShortLongAccessType linkedType = new ShortLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public ShortLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new ShortLongAccessType( img );
	}

	public short get()
	{
		return getValue();
	}

	public void set( final short b )
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
		set( ( short ) f );
	}

	@Override
	public void setInteger( final long f )
	{
		set( ( short ) f );
	}

	@Override
	public void setBigInteger( final BigInteger b )
	{
		set( b.shortValue() );
	}

	@Override
	public double getMaxValue()
	{
		return Short.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return Short.MIN_VALUE;
	}

	@Override
	public ShortLongAccessType createVariable()
	{
		return new ShortLongAccessType( ( short ) 0 );
	}

	@Override
	public ShortLongAccessType copy()
	{
		return new ShortLongAccessType( getValue() );
	}
}
