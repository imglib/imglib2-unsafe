package net.imglib2.type.numeric.complex;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypeaccess.array.DoubleArray;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedDoubleLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.Fraction;

public class ComplexDoubleLongAccessType extends AbstractComplexType< ComplexDoubleLongAccessType > implements NativeLongAccessType< ComplexDoubleLongAccessType >
{
	private long i = 0;

	// the indices for real and imaginary value
	private long realI = 0, imaginaryI = 1;

	final protected NativeLongAccessImg< ?, ? extends DoubleLongAccess > img;

	// the DataAccess that holds the information
	protected DoubleLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public ComplexDoubleLongAccessType( final NativeLongAccessImg< ?, ? extends DoubleLongAccess > complexfloatStorage )
	{
		img = complexfloatStorage;
	}

	// this is the constructor if you want it to be a variable
	public ComplexDoubleLongAccessType( final double r, final double i )
	{
		img = null;
		dataAccess = new WrappedDoubleLongAccess<>( new DoubleArray( 2 ) );
		set( r, i );
	}

	// this is the constructor if you want to specify the dataAccess
	public ComplexDoubleLongAccessType( final DoubleLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public ComplexDoubleLongAccessType()
	{
		this( 0, 0 );
	}

	@Override
	public NativeLongAccessImg< ComplexDoubleLongAccessType, ? extends DoubleLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< ComplexDoubleLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< ComplexDoubleLongAccessType, ? extends DoubleLongAccess > container = storageFactory.createDoubleInstance( dim, new Fraction( 2, 1 ) );

		// create a Type that is linked to the container
		final ComplexDoubleLongAccessType linkedType = new ComplexDoubleLongAccessType( container );

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
	public ComplexDoubleLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new ComplexDoubleLongAccessType( img );
	}

	@Override
	public float getRealFloat()
	{
		return ( float ) dataAccess.getValue( realI );
	}

	@Override
	public double getRealDouble()
	{
		return dataAccess.getValue( realI );
	}

	@Override
	public float getImaginaryFloat()
	{
		return ( float ) dataAccess.getValue( imaginaryI );
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
		dataAccess.setValue( realI, r );
	}

	@Override
	public void setImaginary( final float i )
	{
		dataAccess.setValue( imaginaryI, i );
	}

	@Override
	public void setImaginary( final double i )
	{
		dataAccess.setValue( imaginaryI, i );
	}

	public void set( final double r, final double i )
	{
		dataAccess.setValue( realI, r );
		dataAccess.setValue( imaginaryI, i );
	}

	@Override
	public void set( final ComplexDoubleLongAccessType c )
	{
		setReal( c.getRealDouble() );
		setImaginary( c.getImaginaryDouble() );
	}

	@Override
	public ComplexDoubleLongAccessType createVariable()
	{
		return new ComplexDoubleLongAccessType( 0, 0 );
	}

	@Override
	public ComplexDoubleLongAccessType copy()
	{
		return new ComplexDoubleLongAccessType( getRealFloat(), getImaginaryFloat() );
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
	public boolean valueEquals( final ComplexDoubleLongAccessType t )
	{
		return
				getRealDouble() == t.getRealDouble() &&
				getImaginaryDouble() == t.getImaginaryDouble();
	}
}
