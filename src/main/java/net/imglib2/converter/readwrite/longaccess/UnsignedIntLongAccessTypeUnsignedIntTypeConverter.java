package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.type.numeric.integer.UnsignedIntLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedIntType;

public class UnsignedIntLongAccessTypeUnsignedIntTypeConverter implements SamplerConverter< UnsignedIntLongAccessType, UnsignedIntType >
{

	@Override
	public UnsignedIntType convert( final Sampler< ? extends UnsignedIntLongAccessType > sampler )
	{
		return new UnsignedIntType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements IntAccess
	{

		private final UnsignedIntLongAccessType type;

		public ConvertedAccess( final UnsignedIntLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public int getValue( final int index )
		{
			return type.getInteger();
		}

		@Override
		public void setValue( final int index, final int value )
		{
			type.setInteger( value );
		}

	}

}
