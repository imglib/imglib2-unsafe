package net.imglib2.type.numeric.real;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.NativeLongAccessImgFactory;
import net.imglib2.img.basictypeaccess.array.DoubleArray;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedDoubleLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.util.Fraction;

public class DoubleLongAccessType extends AbstractRealType< DoubleLongAccessType > implements NativeLongAccessType< DoubleLongAccessType >
{
	private long i = 0;

	final protected NativeLongAccessImg< ?, ? extends DoubleLongAccess > img;

	// the DataAccess that holds the information
	protected DoubleLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public DoubleLongAccessType( final NativeLongAccessImg< ?, ? extends DoubleLongAccess > doubleStorage )
	{
		img = doubleStorage;
	}

	// this is the constructor if you want it to be a variable
	public DoubleLongAccessType( final double value )
	{
		img = null;
		dataAccess = new WrappedDoubleLongAccess<>( new DoubleArray( 1 ) );
		set( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public DoubleLongAccessType( final DoubleLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public DoubleLongAccessType()
	{
		this( 0 );
	}

	@Override
	public NativeLongAccessImg< DoubleLongAccessType, ? extends DoubleLongAccess > createSuitableNativeImg( final NativeLongAccessImgFactory< DoubleLongAccessType > storageFactory, final long dim[] )
	{
		// create the container
		final NativeLongAccessImg< DoubleLongAccessType, ? extends DoubleLongAccess > container = storageFactory.createDoubleInstance( dim, new Fraction() );

		// create a Type that is linked to the container
		final DoubleLongAccessType linkedType = new DoubleLongAccessType( container );

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
	public DoubleLongAccessType duplicateTypeOnSameNativeImg()
	{
		return new DoubleLongAccessType( img );
	}

	public double get()
	{
		return dataAccess.getValue( i );
	}

	public void set( final double f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public float getRealFloat()
	{
		return ( float ) get();
	}

	@Override
	public double getRealDouble()
	{
		return get();
	}

	@Override
	public void setReal( final float real )
	{
		set( real );
	}

	@Override
	public void setReal( final double real )
	{
		set( real );
	}

	@Override
	public double getMaxValue()
	{
		return Double.MAX_VALUE;
	}

	@Override
	public double getMinValue()
	{
		return -Double.MAX_VALUE;
	}

	@Override
	public double getMinIncrement()
	{
		return Double.MIN_VALUE;
	}

	@Override
	public DoubleLongAccessType createVariable()
	{
		return new DoubleLongAccessType( 0 );
	}

	@Override
	public DoubleLongAccessType copy()
	{
		return new DoubleLongAccessType( get() );
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction();
	}

	@Override
	public void incIndex()
	{
		++i;
	}

	@Override
	public void decIndex()
	{
		--i;
	}

	@Override
	public int getBitsPerPixel()
	{
		return Double.SIZE;
	}

	@Override
	public boolean valueEquals( final DoubleLongAccessType t )
	{
		return get() == t.get();
	}

	@Override
	public void updateIndex( final long k )
	{
		this.i = k;
	}

	@Override
	public long getIndexLong()
	{
		return i;
	}

	@Override
	public void incIndex( final long increment )
	{
		i += increment;
	}

	@Override
	public void decIndex( final long decrement )
	{
		i -= decrement;
	}

	private static final NativeLongAccessTypeFactory< DoubleLongAccessType, ? > TYPE_FACTORY = NativeLongAccessTypeFactory.DOUBLE( DoubleLongAccessType::new );

	@Override
	public NativeLongAccessTypeFactory< DoubleLongAccessType, ? > getNativeLongAccessTypeFactory()
	{
		return TYPE_FACTORY;
	}

}
