package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypelongaccess.FloatLongAccess;

public class FloatUnsafe extends AbstractStridedUnsafeLongAccess implements FloatLongAccess
{

	private final long address;

	public FloatUnsafe( final long address )
	{
		super( Float.BYTES );
		this.address = address;
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

}
