package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.LongAccess;
import net.imglib2.type.numeric.integer.LongLongAccessType;
import net.imglib2.type.numeric.integer.LongType;

public class LongLongAccessTypeLongTypeConverter implements SamplerConverter< LongLongAccessType, LongType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public LongType convert( final Sampler< ? extends LongLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new LongType( access );
	}

	public static class ConvertedAccess implements LongAccess
	{

		LongLongAccessType type = null;

		public void setType( final LongLongAccessType type )
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