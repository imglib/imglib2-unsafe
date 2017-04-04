package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.LongLongAccess;

public class LongUnsafe extends AbstractStridedUnsafeLongAccess implements LongLongAccess
{

	private final long address;

	public LongUnsafe( final long address )
	{
		super( Long.BYTES );
		this.address = address;
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

}
