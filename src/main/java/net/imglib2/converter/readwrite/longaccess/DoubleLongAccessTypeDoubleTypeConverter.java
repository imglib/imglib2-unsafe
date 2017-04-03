package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.DoubleAccess;
import net.imglib2.type.numeric.real.DoubleLongAccessType;
import net.imglib2.type.numeric.real.DoubleType;

public class DoubleLongAccessTypeDoubleTypeConverter implements SamplerConverter< DoubleLongAccessType, DoubleType >
{

	@Override
	public DoubleType convert( final Sampler< ? extends DoubleLongAccessType > sampler )
	{
		return new DoubleType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements DoubleAccess
	{

		private final DoubleLongAccessType type;

		public ConvertedAccess( final DoubleLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public double getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final double value )
		{
			type.set( value );
		}

	}

}
