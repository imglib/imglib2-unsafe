package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
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
}
