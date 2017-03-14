package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.FloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningFloatUnsafe extends AbstractOwningUnsafe implements FloatLongAccess
{

	private final FloatUnsafe unsafe;

	public OwningFloatUnsafe( final long numEntitites )
	{
		super( UnsafeUtil.create( numEntitites * Float.BYTES ) );
		this.unsafe = new FloatUnsafe( owner.getAddress() );
	}

	@Override
	public float getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final float value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public float getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final float value )
	{
		unsafe.setValue( index, value );
	}

}
