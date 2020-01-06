package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileLongAccess;
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.LongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningLongUnsafe extends AbstractOwningUnsafe implements LongLongAccess, UnsafeAccess< OwningLongUnsafe >, VolatileLongAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final LongUnsafe unsafe;

	private final long numEntities;

	public OwningLongUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningLongUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities * Integer.BYTES ) );
		this.unsafe = new LongUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
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

	@Override
	public OwningLongUnsafe createAccess( final long numEntities )
	{
		return new OwningLongUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.LONG;
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
