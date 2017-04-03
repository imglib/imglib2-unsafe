package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ShortAccess;
import net.imglib2.type.numeric.integer.UnsignedShortLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedShortType;

public class UnsignedShortLongAccessTypeUnsignedShortTypeConverter implements SamplerConverter< UnsignedShortLongAccessType, UnsignedShortType >
{

	@Override
	public UnsignedShortType convert( final Sampler< ? extends UnsignedShortLongAccessType > sampler )
	{
		return new UnsignedShortType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements ShortAccess
	{

		private final UnsignedShortLongAccessType type;

		public ConvertedAccess( final UnsignedShortLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public short getValue( final int index )
		{
			return ( short ) type.get();
		}

		@Override
		public void setValue( final int index, final short value )
		{
			type.set( value );
		}

	}

}
