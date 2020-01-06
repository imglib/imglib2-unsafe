package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileDoubleAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;

public class DoubleUnsafe extends AbstractStridedUnsafeLongAccess implements DoubleLongAccess, VolatileDoubleAccess
{

	private final long address;

	private final Object ownerReference;

	public DoubleUnsafe( final long address )
	{
		this( address, null );
	}

	public DoubleUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public DoubleUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public DoubleUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Double.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public double getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final double value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public double getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getDouble( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final double value )
	{
		UnsafeUtil.UNSAFE.putDouble( getPosition( address, index ), value );
	}

	public long getAddres()
	{
		return address;
	}

	@Override
	public void finalize() throws Throwable
	{
		try
		{
			if ( this.ownerReference instanceof Runnable )
				( ( Runnable ) ownerReference ).run();
		}
		finally
		{
			super.finalize();
		}
	}

}
