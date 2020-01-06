package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileShortAccess;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.ShortUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningShortUnsafe extends AbstractOwningUnsafe implements ShortLongAccess, UnsafeAccess< OwningShortUnsafe >, VolatileShortAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final ShortUnsafe unsafe;

	private final long numEntities;

	public OwningShortUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningShortUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities * Integer.BYTES ) );
		this.unsafe = new ShortUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
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

	@Override
	public OwningShortUnsafe createAccess( final long numEntities )
	{
		return new OwningShortUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.SHORT;
	}

	@Override
	public long getSize()
	{
		return this.numEntities;
	}

	@Override
	public boolean isValid()
	{
		return unsafe.isValid();
	}

}
