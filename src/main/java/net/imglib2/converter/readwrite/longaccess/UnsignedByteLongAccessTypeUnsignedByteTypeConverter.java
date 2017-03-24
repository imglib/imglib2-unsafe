package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ByteAccess;
import net.imglib2.type.numeric.integer.UnsignedByteLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedByteType;

public class UnsignedByteLongAccessTypeUnsignedByteTypeConverter implements SamplerConverter< UnsignedByteLongAccessType, UnsignedByteType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public UnsignedByteType convert( final Sampler< ? extends UnsignedByteLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new UnsignedByteType( access );
	}

	public static class ConvertedAccess implements ByteAccess
	{

		UnsignedByteLongAccessType type = null;

		public void setType( final UnsignedByteLongAccessType type )
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