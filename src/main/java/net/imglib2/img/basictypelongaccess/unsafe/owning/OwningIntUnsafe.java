package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.IntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningIntUnsafe extends AbstractOwningUnsafe implements IntLongAccess
{

	private final IntUnsafe unsafe;

	public OwningIntUnsafe( final long numEntitites )
	{
		super( UnsafeUtil.create( numEntitites * Integer.BYTES ) );
		this.unsafe = new IntUnsafe( owner.getAddress() );
	}

	@Override
	public int getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final int value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public int getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final int value )
	{
		unsafe.setValue( index, value );
	}

}
