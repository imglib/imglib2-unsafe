package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ShortAccess;
import net.imglib2.type.numeric.integer.UnsignedShortLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedShortType;

public class UnsignedShortLongAccessTypeUnsignedShortTypeConverter implements SamplerConverter< UnsignedShortLongAccessType, UnsignedShortType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public UnsignedShortType convert( final Sampler< ? extends UnsignedShortLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new UnsignedShortType( access );
	}

	public static class ConvertedAccess implements ShortAccess
	{

		UnsignedShortLongAccessType type = null;

		public void setType( final UnsignedShortLongAccessType type )
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
