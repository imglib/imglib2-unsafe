package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.IntLongAccess;

public class IntUnsafe extends AbstractStridedUnsafeLongAccess implements IntLongAccess
{

	private final long address;

	public IntUnsafe( final long address )
	{
		super( Integer.BYTES );
		this.address = address;
	}

	@Override
	public int getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final int value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public int getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getInt( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final int value )
	{
		UnsafeUtil.UNSAFE.putInt( getPosition( address, index ), value );
	}

	public long getAddres()
	{
		return address;
	}

}
