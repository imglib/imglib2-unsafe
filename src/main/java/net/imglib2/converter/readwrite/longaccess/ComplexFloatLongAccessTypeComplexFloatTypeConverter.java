/*-
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2021 Howard Hughes Medical Institute.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package net.imglib2.converter.readwrite.longaccess;

import net.imglib2.Sampler;
import net.imglib2.converter.readwrite.SamplerConverter;
import net.imglib2.img.basictypeaccess.FloatAccess;
import net.imglib2.type.numeric.complex.ComplexFloatLongAccessType;
import net.imglib2.type.numeric.complex.ComplexFloatType;

public class ComplexFloatLongAccessTypeComplexFloatTypeConverter implements SamplerConverter< ComplexFloatLongAccessType, ComplexFloatType >
{

	@Override
	public ComplexFloatType convert( final Sampler< ? extends ComplexFloatLongAccessType > sampler )
	{
		return new ComplexFloatType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements FloatAccess
	{

		private final ComplexFloatLongAccessType type;

		public ConvertedAccess( final ComplexFloatLongAccessType type )
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
