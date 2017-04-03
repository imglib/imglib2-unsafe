package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.LongAccess;
import net.imglib2.type.numeric.integer.LongLongAccessType;
import net.imglib2.type.numeric.integer.LongType;

public class LongLongAccessTypeLongTypeConverter implements SamplerConverter< LongLongAccessType, LongType >
{

	@Override
	public LongType convert( final Sampler< ? extends LongLongAccessType > sampler )
	{
		return new LongType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements LongAccess
	{

		private final LongLongAccessType type;

		public ConvertedAccess( final LongLongAccessType type )
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
