package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil.OwningUnsafe;

public abstract class AbstractOwningUnsafe
{

	protected final UnsafeUtil.OwningUnsafe owner;

	public AbstractOwningUnsafe( final OwningUnsafe owner )
	{
		super();
		this.owner = owner;
	}

	public void discard()
	{
		owner.discard();
	}

}
