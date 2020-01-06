package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileShortAccess;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;

public class ShortUnsafe extends AbstractStridedUnsafeLongAccess implements ShortLongAccess, VolatileShortAccess
{

	private final long address;

	private final Object ownerReference;

	public ShortUnsafe( final long address )
	{
		this( address, null );
	}

	public ShortUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public ShortUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public ShortUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Short.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public short getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final short value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public short getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getShort( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final short value )
	{
		UnsafeUtil.UNSAFE.putShort( getPosition( address, index ), value );
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
