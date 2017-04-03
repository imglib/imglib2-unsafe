package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ShortAccess;
import net.imglib2.type.numeric.integer.ShortLongAccessType;
import net.imglib2.type.numeric.integer.ShortType;

public class ShortLongAccessTypeShortTypeConverter implements SamplerConverter< ShortLongAccessType, ShortType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public ShortType convert( final Sampler< ? extends ShortLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new ShortType( access );
	}

	public static class ConvertedAccess implements ShortAccess
	{

		ShortLongAccessType type = null;

		public void setType( final ShortLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public short getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final short value )
		{
			type.set( value );
		}

	}

}
