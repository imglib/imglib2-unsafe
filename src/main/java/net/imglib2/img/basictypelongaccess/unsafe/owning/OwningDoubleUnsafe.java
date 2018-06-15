package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.DoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningDoubleUnsafe extends AbstractOwningUnsafe implements DoubleLongAccess, UnsafeAccess< OwningDoubleUnsafe >
{

	private final DoubleUnsafe unsafe;

	private final long numEntities;

	public OwningDoubleUnsafe( final long numEntities )
	{
		super( UnsafeUtil.create( numEntities * Double.BYTES ) );
		this.unsafe = new DoubleUnsafe( owner.getAddress(), this );
		this.numEntities = numEntities;
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

	@Override
	public OwningDoubleUnsafe createAccess( final long numEntities )
	{
		return new OwningDoubleUnsafe( numEntities );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.DOUBLE;
	}

	@Override
	public long getSize()
	{
		return numEntities;
	}

}
