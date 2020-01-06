package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileFloatAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;

public class FloatUnsafe extends AbstractStridedUnsafeLongAccess implements FloatLongAccess, VolatileFloatAccess
{

	private final long address;

	private final Object ownerReference;

	public FloatUnsafe( final long address )
	{
		this( address, null );
	}

	public FloatUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public FloatUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public FloatUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Float.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public float getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final float value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public float getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getFloat( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final float value )
	{
		UnsafeUtil.UNSAFE.putFloat( getPosition( address, index ), value );
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
