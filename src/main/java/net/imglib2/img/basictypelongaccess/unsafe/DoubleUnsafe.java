package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.DoubleLongAccess;

public class DoubleUnsafe extends AbstractStridedUnsafeLongAccess implements DoubleLongAccess
{

	private final long address;

	private final Object ownerReference;

	private final Runnable onFinalize;

	public DoubleUnsafe( final long address )
	{
		this( address, null, () -> {} );
	}

	public DoubleUnsafe( final long address, final Object ownerReference, final Runnable onFinalize )
	{
		super( Double.BYTES );
		this.address = address;
		this.ownerReference = ownerReference;
		this.onFinalize = onFinalize;
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
	public void finalize()
	{
		onFinalize.run();
	}

}
