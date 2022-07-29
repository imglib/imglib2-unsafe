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
import net.imglib2.img.basictypeaccess.BooleanAccess;
import net.imglib2.img.basictypeaccess.ByteAccess;
import net.imglib2.type.logic.NativeBoolType;
import net.imglib2.type.numeric.integer.ByteLongAccessType;
import net.imglib2.type.numeric.integer.ByteType;

/**
 * A {@link SamplerConverter} that converts {@link ByteLongAccessType} into
 * {@link NativeBoolType}. {@link NativeBoolType}s are fundamentally 8 bits,
 * which is also the size of a {@link ByteType}; even sizes make this conversion
 * sensical.
 * 
 * @author Gabriel Selzer
 */
public class ByteLongAccessTypeNativeBoolTypeConverter implements SamplerConverter< ByteLongAccessType, NativeBoolType >
{

	@Override
	public NativeBoolType convert( final Sampler< ? extends ByteLongAccessType > sampler )
	{
		return new NativeBoolType( new ConvertedAccess( sampler.get() ) );
	}

	public static class ConvertedAccess implements BooleanAccess
	{
		private static final byte ONE = ( byte ) 1;

		private static final byte ZERO = ( byte ) 0;

		private final ByteLongAccessType type;

		public ConvertedAccess( final ByteLongAccessType type )
		{
			this.type = type;
		}

		@Override
		public boolean getValue( final int index )
		{
			return type.get() > 0;
		}

		@Override
		public void setValue( final int index, final boolean value )
		{
			type.set( value ? ONE : ZERO );
		}

	}

}
