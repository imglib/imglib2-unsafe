package net.imglib2.converter.longaccess;

import net.imglib2.converter.Converter;
import net.imglib2.type.numeric.ARGBLongAccessType;
import net.imglib2.type.numeric.ARGBType;

public class ARGBConverter implements Converter< ARGBLongAccessType, ARGBType >
{

	@Override
	public void convert( final ARGBLongAccessType input, final ARGBType output )
	{
		output.set( input.get() );
	}

}
