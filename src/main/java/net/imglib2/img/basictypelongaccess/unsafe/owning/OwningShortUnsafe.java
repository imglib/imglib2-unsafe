package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.ShortUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningShortUnsafe extends AbstractOwningUnsafe implements ShortLongAccess
{

	private final ShortUnsafe unsafe;

	public OwningShortUnsafe( final long numEntitites )
	{
		super( UnsafeUtil.create( numEntitites * Integer.BYTES ) );
		this.unsafe = new ShortUnsafe( owner.getAddress() );
	}

	@Override
	public short getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final short value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public short getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final short value )
	{
		unsafe.setValue( index, value );
	}

}
