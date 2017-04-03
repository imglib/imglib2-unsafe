package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.type.numeric.integer.IntLongAccessType;
import net.imglib2.type.numeric.integer.IntType;

public class IntLongAccessTypeIntTypeConverter implements SamplerConverter< IntLongAccessType, IntType >
{

	@Override
	public IntType convert( final Sampler< ? extends IntLongAccessType > sampler )
	{
		return new IntType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements IntAccess
	{

		private final IntLongAccessType type;

		public ConvertedAccess( final IntLongAccessType type )
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
