package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.ByteAccess;
import net.imglib2.type.numeric.integer.ByteLongAccessType;
import net.imglib2.type.numeric.integer.ByteType;

public class ByteLongAccessTypeByteTypeConverter implements SamplerConverter< ByteLongAccessType, ByteType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public ByteType convert( final Sampler< ? extends ByteLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new ByteType( access );
	}

	public static class ConvertedAccess implements ByteAccess
	{

		ByteLongAccessType type = null;

		public void setType( final ByteLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public byte getValue( final int index )
		{
			return type.get();
		}

		@Override
		public void setValue( final int index, final byte value )
		{
			type.set( value );
		}

	}

}
