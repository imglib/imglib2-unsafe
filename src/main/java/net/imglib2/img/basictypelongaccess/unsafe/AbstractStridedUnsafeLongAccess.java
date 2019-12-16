package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileAccess;

public class AbstractStridedUnsafeLongAccess implements VolatileAccess
{
	protected static final boolean DEFAULT_IS_VALID = true;

	protected final int stride;

	private final boolean isValid;

	public AbstractStridedUnsafeLongAccess( final int stride, final boolean isValid )
	{
		super();
		this.stride = stride;
		this.isValid = isValid;
	}

	protected long getPosition( final long offset, final long index )
	{
		return offset + index * stride;
	}

	@Override
	public boolean isValid()
	{
		return isValid;
	}

}
