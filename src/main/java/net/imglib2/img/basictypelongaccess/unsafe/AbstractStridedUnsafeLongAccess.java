package net.imglib2.img.basictypelongaccess.unsafe;

public class AbstractStridedUnsafeLongAccess
{
	protected final int stride;


	public AbstractStridedUnsafeLongAccess( final int stride )
	{
		super();
		this.stride = stride;
	}

	protected long getPosition( final long offset, final long index )
	{
		return offset + index * stride;
	}

}
