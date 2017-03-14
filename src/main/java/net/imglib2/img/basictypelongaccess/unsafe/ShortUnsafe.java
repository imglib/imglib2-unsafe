package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.ShortLongAccess;

public class ShortUnsafe extends AbstractStridedUnsafeLongAccess implements ShortLongAccess
{

	private final long address;

	public ShortUnsafe( final long address )
	{
		super( Short.BYTES );
		this.address = address;
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

}
