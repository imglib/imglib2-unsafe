package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.DoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningDoubleUnsafe extends AbstractOwningUnsafe implements DoubleLongAccess
{

	private final DoubleUnsafe unsafe;

	public OwningDoubleUnsafe( final long numEntities )
	{
		super( UnsafeUtil.create( numEntities * Double.BYTES ) );
		this.unsafe = new DoubleUnsafe( owner.getAddress() );
	}

	@Override
	public double getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final double value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public double getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final double value )
	{
		unsafe.setValue( index, value );
	}

}
