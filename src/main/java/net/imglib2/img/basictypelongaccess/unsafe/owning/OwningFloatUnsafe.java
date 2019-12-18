package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileFloatAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.FloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningFloatUnsafe extends AbstractOwningUnsafe implements FloatLongAccess, UnsafeAccess< OwningFloatUnsafe >, VolatileFloatAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final FloatUnsafe unsafe;

	private final long numEntities;

	public OwningFloatUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningFloatUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities * Float.BYTES ) );
		this.unsafe = new FloatUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
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

	@Override
	public OwningFloatUnsafe createAccess( final long numEntities )
	{
		return new OwningFloatUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.FLOAT;
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
