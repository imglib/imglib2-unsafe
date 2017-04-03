package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.FloatAccess;
import net.imglib2.type.numeric.real.FloatLongAccessType;
import net.imglib2.type.numeric.real.FloatType;

public class FloatLongAccessTypeFloatTypeConverter implements SamplerConverter< FloatLongAccessType, FloatType >
{

	private final FloatLongAccessTypeFloatTypeConverter.ConvertedAccess access = new ConvertedAccess();

	@Override
	public FloatType convert( final Sampler< ? extends FloatLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new FloatType( access );
	}

	public static class ConvertedAccess implements FloatAccess
	{

		FloatLongAccessType type = null;

		public void setType( final FloatLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public float getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final float value )
		{
			type.set( value );
		}

	}

}
