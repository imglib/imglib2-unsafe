package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.type.numeric.integer.UnsignedIntLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedIntType;

public class UnsignedIntLongAccessTypeUnsignedIntTypeConverter implements SamplerConverter< UnsignedIntLongAccessType, UnsignedIntType >
{

	private final ConvertedAccess access = new ConvertedAccess();

	@Override
	public UnsignedIntType convert( final Sampler< ? extends UnsignedIntLongAccessType > sampler )
	{
		access.setType( sampler.get() );
		return new UnsignedIntType( access );
	}

	public static class ConvertedAccess implements IntAccess
	{

		UnsignedIntLongAccessType type = null;

		public void setType( final UnsignedIntLongAccessType type )
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