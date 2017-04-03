package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.DoubleAccess;
import net.imglib2.type.numeric.complex.ComplexDoubleLongAccessType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;

public class ComplexDoubleLongAccessTypeComplexDoubleTypeConverter implements SamplerConverter< ComplexDoubleLongAccessType, ComplexDoubleType >
{

	@Override
	public ComplexDoubleType convert( final Sampler< ? extends ComplexDoubleLongAccessType > sampler )
	{
		return new ComplexDoubleType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements DoubleAccess
	{

		private final ComplexDoubleLongAccessType type;

		public ConvertedAccess( final ComplexDoubleLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public double getValue( final int index )
		{
			return index == 0 ? type.getRealDouble() : type.getImaginaryDouble();
		}

		@Override
		public void setValue( final int index, final double value )
		{
			if ( index == 0 )
				type.setReal( value );
			else
				type.setImaginary( value );
		}

	}

}
