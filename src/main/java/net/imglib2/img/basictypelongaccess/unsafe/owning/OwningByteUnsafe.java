package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileByteAccess;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.ByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningByteUnsafe extends AbstractOwningUnsafe implements ByteLongAccess, UnsafeAccess< OwningByteUnsafe >, VolatileByteAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final ByteUnsafe unsafe;

	private final long numEntities;

	public OwningByteUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningByteUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities ) );
		this.unsafe = new ByteUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
	}

	@Override
	public byte getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final byte value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public byte getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final byte value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public OwningByteUnsafe createAccess( final long numEntities )
	{
		return new OwningByteUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.BYTE;
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
