package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.DoubleLongAccess;

public class DoubleUnsafe extends AbstractStridedUnsafeLongAccess implements DoubleLongAccess
{

	private final long address;

	public DoubleUnsafe( final long address )
	{
		super( Double.BYTES );
		this.address = address;
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

}
