package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ShortAccess;
import net.imglib2.type.numeric.integer.ShortLongAccessType;
import net.imglib2.type.numeric.integer.ShortType;

public class ShortLongAccessTypeShortTypeConverter implements SamplerConverter< ShortLongAccessType, ShortType >
{

	@Override
	public ShortType convert( final Sampler< ? extends ShortLongAccessType > sampler )
	{
		return new ShortType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements ShortAccess
	{

		private final ShortLongAccessType type;

		public ConvertedAccess( final ShortLongAccessType type )
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
