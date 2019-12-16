package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileAccess;
import net.imglib2.img.basictypelongaccess.CharLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.ByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.CharUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

public class OwningCharUnsafe extends AbstractOwningUnsafe implements CharLongAccess, UnsafeAccess< OwningCharUnsafe >, VolatileAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final CharUnsafe unsafe;

	private final long numEntities;

	public OwningCharUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	public OwningCharUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities ) );
		this.unsafe = new CharUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
	}

	@Override
	public char getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final char value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public char getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final char value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public OwningCharUnsafe createAccess( final long numEntities )
	{
		return new OwningCharUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.CHAR;
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
