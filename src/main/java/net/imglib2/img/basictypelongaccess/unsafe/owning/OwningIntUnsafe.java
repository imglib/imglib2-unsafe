package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileAccess;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.IntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningIntUnsafe extends AbstractOwningUnsafe implements IntLongAccess, UnsafeAccess< OwningIntUnsafe >, VolatileAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final IntUnsafe unsafe;

	private final long numEntities;

	public OwningIntUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningIntUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities * Integer.BYTES ) );
		this.unsafe = new IntUnsafe( owner.getAddress(), this, isValid );
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
		return new OwningIntUnsafe( numEntities, isValid() );
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

	@Override
	public boolean isValid()
	{
		return unsafe.isValid();
	}

}
