package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ByteAccess;
import net.imglib2.type.numeric.integer.UnsignedByteLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedByteType;

public class UnsignedByteLongAccessTypeUnsignedByteTypeConverter implements SamplerConverter< UnsignedByteLongAccessType, UnsignedByteType >
{

	@Override
	public UnsignedByteType convert( final Sampler< ? extends UnsignedByteLongAccessType > sampler )
	{
		return new UnsignedByteType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements ByteAccess
	{

		private final UnsignedByteLongAccessType type;

		public ConvertedAccess( final UnsignedByteLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public byte getValue( final int index )
		{
			return ( byte ) type.get();
		}

		@Override
		public void setValue( final int index, final byte value )
		{
			type.set( value );
		}

	}

}
