package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileAccess;
import net.imglib2.img.basictypelongaccess.CharLongAccess;

public class CharUnsafe extends AbstractStridedUnsafeLongAccess implements CharLongAccess, VolatileAccess
{

	private final long address;

	private final Object ownerReference;

	public CharUnsafe( final long address )
	{
		this( address, null );
	}

	public CharUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public CharUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public CharUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Character.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public char getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final char value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public char getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getChar( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final char value )
	{
		UnsafeUtil.UNSAFE.putChar( getPosition( address, index ), value );
	}

	public long getAddres()
	{
		return address;
	}

}
