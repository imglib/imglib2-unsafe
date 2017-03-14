package net.imglib2.type.numeric.complex;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedFloatLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.Fraction;

public class ComplexFloatLongAccessType extends AbstractComplexType< ComplexFloatLongAccessType > implements NativeLongAccessType< ComplexFloatLongAccessType >
{
	private long i = 0;

	// the indices for real and imaginary number
	private long realI = 0, imaginaryI = 1;

	final protected NativeLongAccessImg< ?, ? extends FloatLongAccess > img;

	// the DataAccess that holds the information
	protected FloatLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public ComplexFloatLongAccessType( final NativeLongAccessImg< ?, ? extends FloatLongAccess > complexfloatStorage )
	{
		img = complexfloatStorage;
	}

	// this is the constructor if you want it to be a variable
	public ComplexFloatLongAccessType( final float r, final float i )
	{
		img = null;
		dataAccess = new WrappedFloatLongAccess<>( new FloatArray( 2 ) );
		set( r, i );
	}

	// this is the constructor if you want to specify the dataAccess
	public ComplexFloatLongAccessType( final FloatLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public ComplexFloatLongAccessType()
	{
		this( 0, 0 );
	}

	@Override
	public NativeLongAccessImg< ComplexFloatLongAccessType, ? extends FloatLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< ComplexFloatLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< ComplexFloatLongAccessType, ? extends FloatLongAccess > container = storageFactory.createFloatInstance( dim, new Fraction( 2, 1 ) );

		// create a Type that is linked to the container
		final ComplexFloatLongAccessType linkedType = new ComplexFloatLongAccessType( container );

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
	public ComplexFloatLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new ComplexFloatLongAccessType( img );
	}

	@Override
	public float getRealFloat()
	{
		return dataAccess.getValue( realI );
	}

	@Override
	public double getRealDouble()
	{
		return dataAccess.getValue( realI );
	}

	@Override
	public float getImaginaryFloat()
	{
		return dataAccess.getValue( imaginaryI );
	}

	@Override
	public double getImaginaryDouble()
	{
		return dataAccess.getValue( imaginaryI );
	}

	@Override
	public void setReal( final float r )
	{
		dataAccess.setValue( realI, r );
	}

	@Override
	public void setReal( final double r )
	{
		dataAccess.setValue( realI, ( float ) r );
	}

	@Override
	public void setImaginary( final float i )
	{
		dataAccess.setValue( imaginaryI, i );
	}

	@Override
	public void setImaginary( final double i )
	{
		dataAccess.setValue( imaginaryI, ( float ) i );
	}

	public void set( final float r, final float i )
	{
		dataAccess.setValue( realI, r );
		dataAccess.setValue( imaginaryI, i );
	}

	@Override
	public void add( final ComplexFloatLongAccessType c )
	{
		setReal( getRealFloat() + c.getRealFloat() );
		setImaginary( getImaginaryFloat() + c.getImaginaryFloat() );
	}

	@Override
	public void div( final ComplexFloatLongAccessType c )
	{
		final float a1 = getRealFloat();
		final float b1 = getImaginaryFloat();
		final float c1 = c.getRealFloat();
		final float d1 = c.getImaginaryFloat();

		setReal( ( a1 * c1 + b1 * d1 ) / ( c1 * c1 + d1 * d1 ) );
		setImaginary( ( b1 * c1 - a1 * d1 ) / ( c1 * c1 + d1 * d1 ) );
	}

	@Override
	public void mul( final ComplexFloatLongAccessType t )
	{
		// a + bi
		final float a = getRealFloat();
		final float b = getImaginaryFloat();

		// c + di
		final float c = t.getRealFloat();
		final float d = t.getImaginaryFloat();

		setReal( a * c - b * d );
		setImaginary( a * d + b * c );
	}

	@Override
	public void sub( final ComplexFloatLongAccessType c )
	{
		setReal( getRealFloat() - c.getRealFloat() );
		setImaginary( getImaginaryFloat() - c.getImaginaryFloat() );
	}

	@Override
	public void complexConjugate()
	{
		setImaginary( -getImaginaryFloat() );
	}

	public void switchRealComplex()
	{
		final float a = getRealFloat();
		setReal( getImaginaryFloat() );
		setImaginary( a );
	}

	@Override
	public void set( final ComplexFloatLongAccessType c )
	{
		setReal( c.getRealFloat() );
		setImaginary( c.getImaginaryFloat() );
	}

	@Override
	public ComplexFloatLongAccessType createVariable()
	{
		return new ComplexFloatLongAccessType( 0, 0 );
	}

	@Override
	public ComplexFloatLongAccessType copy()
	{
		return new ComplexFloatLongAccessType( getRealFloat(), getImaginaryFloat() );
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction( 2, 1 );
	}

	@Override
	public void updateIndex( final long index )
	{
		this.i = index;
		realI = index * 2;
		imaginaryI = index * 2 + 1;
	}

	@Override
	public void incIndex()
	{
		++i;
		realI += 2;
		imaginaryI += 2;
	}

	@Override
	public void incIndex( final long increment )
	{
		i += increment;

		final long inc2 = 2 * increment;
		realI += inc2;
		imaginaryI += inc2;
	}

	@Override
	public void decIndex()
	{
		--i;
		realI -= 2;
		imaginaryI -= 2;
	}

	@Override
	public void decIndex( final long decrement )
	{
		i -= decrement;
		final long dec2 = 2 * decrement;
		realI -= dec2;
		imaginaryI -= dec2;
	}

	@Override
	public long getIndexLong()
	{
		return i;
	}

	@Override
	public boolean valueEquals( final ComplexFloatLongAccessType t )
	{
		return getRealFloat() == t.getRealFloat() &&
				getImaginaryFloat() == t.getImaginaryFloat();
	}
}
