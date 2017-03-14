package net.imglib2.type.numeric;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypeaccess.array.IntArray;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedIntLongAccess;
import net.imglib2.type.AbstractNativeLongAccessType;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

public class ARGBLongAccessType extends AbstractNativeLongAccessType< ARGBLongAccessType > implements NumericType< ARGBLongAccessType >
{
	final protected NativeLongAccessImg< ?, ? extends IntLongAccess > img;

	// the DataAccess that holds the information
	protected IntLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public ARGBLongAccessType( final NativeLongAccessImg< ?, ? extends IntLongAccess > intStorage )
	{
		img = intStorage;
	}

	// this is the constructor if you want it to be a variable
	public ARGBLongAccessType( final int value )
	{
		img = null;
		dataAccess = new WrappedIntLongAccess<>( new IntArray( 1 ) );
		set( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public ARGBLongAccessType( final IntLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public ARGBLongAccessType()
	{
		this( 0 );
	}

	@Override
	public NativeLongAccessImg< ARGBLongAccessType, ? extends IntLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< ARGBLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< ARGBLongAccessType, ? extends IntLongAccess > container = storageFactory.createIntInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final ARGBLongAccessType linkedType = new ARGBLongAccessType( container );

		// pass it to the NativeContainer
		container.setLinkedType( linkedType );

		return container;
	}

	@Override
	public void updateContainer( final Object c )
	{
		dataAccess = img.update( c );
	}

	@Override
	public ARGBLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new ARGBLongAccessType( img );
	}

	final public static int rgba( final int r, final int g, final int b, final int a )
	{
		return ( r & 0xff ) << 16 | ( g & 0xff ) << 8 | b & 0xff | ( a & 0xff ) << 24;
	}

	final public static int rgba( final float r, final float g, final float b, final float a )
	{
		return rgba( Util.round( r ), Util.round( g ), Util.round( b ), Util.round( a ) );
	}

	final public static int rgba( final double r, final double g, final double b, final double a )
	{
		return rgba( ( int ) Util.round( r ), ( int ) Util.round( g ), ( int ) Util.round( b ), ( int ) Util.round( a ) );
	}

	final public static int red( final int value )
	{
		return value >> 16 & 0xff;
	}

	final public static int green( final int value )
	{
		return value >> 8 & 0xff;
	}

	final public static int blue( final int value )
	{
		return value & 0xff;
	}

	final public static int alpha( final int value )
	{
		return value >> 24 & 0xff;
	}

	public int get()
	{
		return dataAccess.getValue( i );
	}

	public void set( final int f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public void mul( final float c )
	{
		final int value = get();
		set( rgba( red( value ) * c, green( value ) * c, blue( value ) * c, alpha( value ) * c ) );
	}

	@Override
	public void mul( final double c )
	{
		final int value = get();
		set( rgba( red( value ) * c, green( value ) * c, blue( value ) * c, alpha( value ) * c ) );
	}

	@Override
	public void add( final ARGBLongAccessType c )
	{
		final int value1 = get();
		final int value2 = c.get();

		set( rgba( red( value1 ) + red( value2 ), green( value1 ) + green( value2 ), blue( value1 ) + blue( value2 ), alpha( value1 ) + alpha( value2 ) ) );
	}

	@Override
	public void div( final ARGBLongAccessType c )
	{
		final int value1 = get();
		final int value2 = c.get();

		set( rgba( red( value1 ) / red( value2 ), green( value1 ) / green( value2 ), blue( value1 ) / blue( value2 ), alpha( value1 ) / alpha( value2 ) ) );
	}

	@Override
	public void mul( final ARGBLongAccessType c )
	{
		final int value1 = get();
		final int value2 = c.get();

		set( rgba( red( value1 ) * red( value2 ), green( value1 ) * green( value2 ), blue( value1 ) * blue( value2 ), alpha( value1 ) * alpha( value2 ) ) );
	}

	@Override
	public void sub( final ARGBLongAccessType c )
	{
		final int value1 = get();
		final int value2 = c.get();

		set( rgba( red( value1 ) - red( value2 ), green( value1 ) - green( value2 ), blue( value1 ) - blue( value2 ), alpha( value1 ) - alpha( value2 ) ) );
	}

	@Override
	public void set( final ARGBLongAccessType c )
	{
		set( c.get() );
	}

	@Override
	public void setOne()
	{
		set( rgba( 1, 1, 1, 1 ) );
	}

	@Override
	public void setZero()
	{
		set( 0 );
	}

	@Override
	public ARGBLongAccessType createVariable()
	{
		return new ARGBLongAccessType( 0 );
	}

	@Override
	public ARGBLongAccessType copy()
	{
		return new ARGBLongAccessType( get() );
	}

	@Override
	public String toString()
	{
		final int rgba = get();
		return "(r=" + red( rgba ) + ",g=" + green( rgba ) + ",b=" + blue( rgba ) + ",a=" + alpha( rgba ) + ")";
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction();
	}

	@Override
	public boolean valueEquals( final ARGBLongAccessType t )
	{
		return get() == t.get();
	}
}
