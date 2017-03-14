package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.ByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningByteUnsafe extends AbstractOwningUnsafe implements ByteLongAccess
{

	private final ByteUnsafe unsafe;

	public OwningByteUnsafe( final long numEntities )
	{
		super( UnsafeUtil.create( numEntities ) );
		this.unsafe = new ByteUnsafe( owner.getAddress() );
	}

	@Override
	public byte getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final byte value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public byte getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final byte value )
	{
		unsafe.setValue( index, value );
	}

}
