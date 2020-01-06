package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileLongAccess;
import net.imglib2.img.basictypelongaccess.LongLongAccess;

public class LongUnsafe extends AbstractStridedUnsafeLongAccess implements LongLongAccess, VolatileLongAccess
{

	private final long address;

	private final Object ownerReference;

	public LongUnsafe( final long address )
	{
		this( address, null );
	}

	public LongUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public LongUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public LongUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Long.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public long getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final long value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public long getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getLong( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final long value )
	{
		UnsafeUtil.UNSAFE.putLong( getPosition( address, index ), value );
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
