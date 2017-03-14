package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.CharLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.CharUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

public class OwningCharUnsafe extends AbstractOwningUnsafe implements CharLongAccess
{

	private final CharUnsafe unsafe;

	public OwningCharUnsafe( final long numEntities )
	{
		super( UnsafeUtil.create( numEntities * Character.BYTES ) );
		this.unsafe = new CharUnsafe( owner.getAddress() );
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

}
