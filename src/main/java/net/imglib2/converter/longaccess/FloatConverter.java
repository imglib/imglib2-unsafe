package net.imglib2.converter.longaccess;

import net.imglib2.converter.Converter;
import net.imglib2.type.numeric.real.FloatLongAccessType;
import net.imglib2.type.numeric.real.FloatType;

public class FloatConverter implements Converter< FloatLongAccessType, FloatType >
{

	@Override
	public void convert( final FloatLongAccessType input, final FloatType output )
	{
		output.set( input.get() );

	}

}
