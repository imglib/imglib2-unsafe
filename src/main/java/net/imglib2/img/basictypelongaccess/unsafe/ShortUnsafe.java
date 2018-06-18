package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.ShortLongAccess;

public class ShortUnsafe extends AbstractStridedUnsafeLongAccess implements ShortLongAccess
{

	private final long address;

	private final Object ownerReference;

	private final Runnable onFinalize;

	public ShortUnsafe( final long address )
	{
		this( address, null, () -> {} );
	}

	public ShortUnsafe( final long address, final Object ownerReference, final Runnable onFinalize )
	{
		super( Short.BYTES );
		this.address = address;
		this.ownerReference = ownerReference;
		this.onFinalize = onFinalize;
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
	public void finalize()
	{
		onFinalize.run();
	}

}
