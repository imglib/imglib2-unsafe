package net.imglib2.type.numeric.integer;

import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.basictypeaccess.array.IntArray;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.wrapped.WrappedIntLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

public abstract class GenericIntLongAccessType< T extends GenericIntLongAccessType< T > > extends AbstractIntegerType< T > implements NativeLongAccessType< T >
{

	long i = 0;

	final protected NativeLongAccessImg< ?, ? extends IntLongAccess > img;

	// the DataAccess that holds the information
	protected IntLongAccess dataAccess;

	// this is the constructor if you want it to read from an array
	public GenericIntLongAccessType( final NativeLongAccessImg< ?, ? extends IntLongAccess > intStorage )
	{
		img = intStorage;
	}

	// this is the constructor if you want it to be a variable
	public GenericIntLongAccessType( final int value )
	{
		img = null;
		dataAccess = new WrappedIntLongAccess<>( new IntArray( 1 ) );
		setValue( value );
	}

	// this is the constructor if you want to specify the dataAccess
	public GenericIntLongAccessType( final IntLongAccess access )
	{
		img = null;
		dataAccess = access;
	}

	// this is the constructor if you want it to be a variable
	public GenericIntLongAccessType()
	{
		this( 0 );
	}

	@Override
	public Fraction getEntitiesPerPixel()
	{
		return new Fraction();
	}

	@Override
	public void updateContainer( final Object c )
	{
		dataAccess = img.update( c );
	}

	protected int getValue()
	{
		return dataAccess.getValue( i );
	}

	protected void setValue( final int f )
	{
		dataAccess.setValue( i, f );
	}

	@Override
	public void mul( final float c )
	{
		final int a = getValue();
		setValue( Util.round( a * c ) );
	}

	@Override
	public void mul( final double c )
	{
		final int a = getValue();
		setValue( ( int ) Util.round( a * c ) );
	}

	@Override
	public void add( final T c )
	{
		final int a = getValue();
		setValue( a + c.getValue() );
	}

	@Override
	public void div( final T c )
	{
		final int a = getValue();
		setValue( a / c.getValue() );
	}

	@Override
	public void mul( final T c )
	{
		final int a = getValue();
		setValue( a * c.getValue() );
	}

	@Override
	public void sub( final T c )
	{
		final int a = getValue();
		setValue( a - c.getValue() );
	}

	@Override
	public int hashCode()
	{
		// NB: Use the same hash code as java.lang.Integer#hashCode().
		return getValue();
	}

	@Override
	public int compareTo( final T c )
	{
		final int a = getValue();
		final int b = c.getValue();
		if ( a > b )
			return 1;
		else if ( a < b )
			return -1;
		else
			return 0;
	}

	@Override
	public void set( final T c )
	{
		setValue( c.getValue() );
	}

	@Override
	public void setOne()
	{
		setValue( 1 );
	}

	@Override
	public void setZero()
	{
		setValue( 0 );
	}

	@Override
	public void inc()
	{
		int a = getValue();
		setValue( ++a );
	}

	@Override
	public void dec()
	{
		int a = getValue();
		setValue( --a );
	}

	@Override
	public String toString()
	{
		return "" + getValue();
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
		return Integer.SIZE;
	}

	@Override
	public boolean valueEquals( final T t )
	{
		return getValue() == t.getValue();
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

}
