package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.LongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningLongUnsafe extends AbstractOwningUnsafe implements LongLongAccess
{

	private final LongUnsafe unsafe;

	public OwningLongUnsafe( final long numEntitites )
	{
		super( UnsafeUtil.create( numEntitites * Integer.BYTES ) );
		this.unsafe = new LongUnsafe( owner.getAddress() );
	}

	@Override
	public long getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final long value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public long getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final long value )
	{
		unsafe.setValue( index, value );
	}

}
