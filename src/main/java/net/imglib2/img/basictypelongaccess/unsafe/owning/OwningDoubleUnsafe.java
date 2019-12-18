package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileDoubleAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.DoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningDoubleUnsafe extends AbstractOwningUnsafe implements DoubleLongAccess, UnsafeAccess< OwningDoubleUnsafe >, VolatileDoubleAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final DoubleUnsafe unsafe;

	private final long numEntities;

	public OwningDoubleUnsafe( final long numEntities  )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningDoubleUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities * Double.BYTES ) );
		this.unsafe = new DoubleUnsafe( owner.getAddress(), this, isValid );
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
		return new OwningDoubleUnsafe( numEntities, unsafe.isValid() );
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

	@Override
	public boolean isValid()
	{
		return unsafe.isValid();
	}

}
