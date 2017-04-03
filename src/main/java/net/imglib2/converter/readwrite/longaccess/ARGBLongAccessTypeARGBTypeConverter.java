package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.type.numeric.ARGBLongAccessType;
import net.imglib2.type.numeric.ARGBType;

public class ARGBLongAccessTypeARGBTypeConverter implements SamplerConverter< ARGBLongAccessType, ARGBType >
{

	@Override
	public ARGBType convert( final Sampler< ? extends ARGBLongAccessType > sampler )
	{
		return new ARGBType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements IntAccess
	{

		private final ARGBLongAccessType type;

		public ConvertedAccess( final ARGBLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public int getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final int value )
		{
			type.set( value );
		}

	}

}
