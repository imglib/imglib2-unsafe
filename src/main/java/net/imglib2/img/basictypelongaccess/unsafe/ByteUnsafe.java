package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileByteAccess;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;

public class ByteUnsafe implements ByteLongAccess, VolatileByteAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final long address;

	private final Object ownerReference;

	private final boolean isValid;

	public ByteUnsafe( final long address )
	{
		this( address, null );
	}

	public ByteUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public ByteUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public ByteUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super();
		this.address = address;
		this.ownerReference = ownerReference;
		this.isValid = true;
	}

	@Override
	public byte getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final byte value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public byte getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getByte( address + index );
	}

	@Override
	public void setValue( final long index, final byte value )
	{
		UnsafeUtil.UNSAFE.putByte( address + index, value );
	}

	public long getAddres()
	{
		return address;
	}

	@Override
	public boolean isValid()
	{
		return this.isValid;
	}

}
