package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.FloatAccess;
import net.imglib2.type.numeric.complex.ComplexFloatLongAccessType;
import net.imglib2.type.numeric.complex.ComplexFloatType;

public class ComplexFloatLongAccessTypeComplexFloatTypeConverter implements SamplerConverter< ComplexFloatLongAccessType, ComplexFloatType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public ComplexFloatType convert( final Sampler< ? extends ComplexFloatLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new ComplexFloatType( access );
	}

	public static class ConvertedAccess implements FloatAccess
	{

		ComplexFloatLongAccessType type = null;

		public void setType( final ComplexFloatLongAccessType type )
		{
			this.type = type;
		}


		@Override
		public float getValue( final int index )
		{
			return index == 0 ? type.getRealFloat() : type.getImaginaryFloat();
		}

		@Override
		public void setValue( final int index, final float value )
		{
			if ( index == 0 )
				type.setReal( value );
			else
				type.setImaginary( value );
		}

	}

}