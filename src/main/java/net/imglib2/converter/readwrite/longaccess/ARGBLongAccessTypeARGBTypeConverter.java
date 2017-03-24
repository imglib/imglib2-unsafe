package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.type.numeric.ARGBLongAccessType;
import net.imglib2.type.numeric.ARGBType;

public class ARGBLongAccessTypeARGBTypeConverter implements SamplerConverter< ARGBLongAccessType, ARGBType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public ARGBType convert( final Sampler< ? extends ARGBLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new ARGBType( access );
	}

	public static class ConvertedAccess implements IntAccess
	{

		ARGBLongAccessType type = null;

		public void setType( final ARGBLongAccessType type )
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