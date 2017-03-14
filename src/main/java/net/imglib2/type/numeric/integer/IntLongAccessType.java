package net.imglib2.type.numeric.integer;

import java.math.BigInteger;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypelongaccess.IntLongAccess;

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

}
