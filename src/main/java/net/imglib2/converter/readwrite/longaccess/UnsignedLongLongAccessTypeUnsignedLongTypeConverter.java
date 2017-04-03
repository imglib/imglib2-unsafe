package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.LongAccess;
import net.imglib2.type.numeric.integer.UnsignedLongLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedLongType;

public class UnsignedLongLongAccessTypeUnsignedLongTypeConverter implements SamplerConverter< UnsignedLongLongAccessType, UnsignedLongType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public UnsignedLongType convert( final Sampler< ? extends UnsignedLongLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new UnsignedLongType( access );
	}

	public static class ConvertedAccess implements LongAccess
	{

		UnsignedLongLongAccessType type = null;

		public void setType( final UnsignedLongLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public long getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final long value )
		{
			type.set( value );
		}

	}

}
