package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.IntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningIntUnsafe extends AbstractOwningUnsafe implements IntLongAccess, UnsafeAccess< OwningIntUnsafe >
{

	private final IntUnsafe unsafe;

	private final long numEntities;

	public OwningIntUnsafe( final long numEntities )
	{
		super( UnsafeUtil.create( numEntities * Integer.BYTES ) );
		this.unsafe = new IntUnsafe( owner.getAddress() );
		this.numEntities = numEntities;
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

	@Override
	public OwningIntUnsafe createAccess( final long numEntities )
	{
		return new OwningIntUnsafe( numEntities );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.INT;
	}

	@Override
	public long getSize()
	{
		return numEntities;
	}

}
