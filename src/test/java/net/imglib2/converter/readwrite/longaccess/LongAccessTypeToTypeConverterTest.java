/*-
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2023 Howard Hughes Medical Institute.
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
import net.imglib2.type.logic.NativeBoolType;
import net.imglib2.type.numeric.complex.ComplexDoubleLongAccessType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;
import net.imglib2.type.numeric.complex.ComplexFloatLongAccessType;
import net.imglib2.type.numeric.complex.ComplexFloatType;
import net.imglib2.type.numeric.integer.ByteLongAccessType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.LongType;
import net.imglib2.type.numeric.integer.ShortLongAccessType;
import net.imglib2.type.numeric.integer.IntLongAccessType;
import net.imglib2.type.numeric.integer.LongLongAccessType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedLongLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedLongType;
import net.imglib2.type.numeric.integer.UnsignedShortLongAccessType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatLongAccessType;
import net.imglib2.type.numeric.real.DoubleLongAccessType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.type.numeric.real.DoubleType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@link SamplerConverter}s of this project
 * 
 * @author Gabriel Selzer
 */
public class LongAccessTypeToTypeConverterTest
{

	private static < T, U > U convert( T access, SamplerConverter< T, U > converter )
	{
		Sampler< T > sampler = new Sampler< T >()
		{

			@Override
			public T get()
			{
				return access;
			}

			@Override
			public Sampler< T > copy()
			{
				return null;
			}
		};
		return converter.convert( sampler );
	}

	@Test
	public void testNativeBoolean()
	{
		ByteLongAccessType access = new ByteLongAccessType( ( byte ) 4 );
		NativeBoolType converted = convert( access, new ByteLongAccessTypeNativeBoolTypeConverter() );
		boolean value = false;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( 0, access.get() );
	}

	@Test
	public void testByte()
	{
		ByteLongAccessType access = new ByteLongAccessType( ( byte ) 4 );
		ByteType converted = convert( access, new ByteLongAccessTypeByteTypeConverter() );
		byte value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testShort()
	{
		ShortLongAccessType access = new ShortLongAccessType( ( short ) 4 );
		ShortType converted = convert( access, new ShortLongAccessTypeShortTypeConverter() );
		short value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testInteger()
	{
		IntLongAccessType access = new IntLongAccessType( 4 );
		IntType converted = convert( access, new IntLongAccessTypeIntTypeConverter() );
		int value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testLong()
	{
		LongLongAccessType access = new LongLongAccessType( 4L );
		LongType converted = convert( access, new LongLongAccessTypeLongTypeConverter() );
		long value = 5L;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testFloat()
	{
		FloatLongAccessType access = new FloatLongAccessType( 4f );
		FloatType converted = convert( access, new FloatLongAccessTypeFloatTypeConverter() );
		float value = 5f;
		converted.set( value );
		assertEquals( value, converted.get(), 1e-6 );
		assertEquals( value, access.get(), 1e-6 );
	}

	@Test
	public void testDouble()
	{
		DoubleLongAccessType access = new DoubleLongAccessType( 4d );
		DoubleType converted = convert( access, new DoubleLongAccessTypeDoubleTypeConverter() );
		double value = 5d;
		converted.set( value );
		assertEquals( value, converted.get(), 1e-6 );
		assertEquals( value, access.get(), 1e-6 );
	}

	@Test
	public void testComplexFloat()
	{
		ComplexFloatLongAccessType access = new ComplexFloatLongAccessType( 4f, 4f );
		ComplexFloatType converted = convert( access, new ComplexFloatLongAccessTypeComplexFloatTypeConverter() );
		float real = 5f;
		float imag = 5f;
		converted.set( real, imag );
		assertEquals( real, converted.getRealDouble(), 1e-6 );
		assertEquals( real, access.getRealDouble(), 1e-6 );
		assertEquals( imag, converted.getImaginaryDouble(), 1e-6 );
		assertEquals( imag, access.getImaginaryDouble(), 1e-6 );
	}

	@Test
	public void testComplexDouble()
	{
		ComplexDoubleLongAccessType access = new ComplexDoubleLongAccessType( 4d, 4d );
		ComplexDoubleType converted = convert( access, new ComplexDoubleLongAccessTypeComplexDoubleTypeConverter() );
		double real = 5d;
		double imag = 5d;
		converted.set( real, imag );
		assertEquals( real, converted.getRealDouble(), 1e-6 );
		assertEquals( real, access.getRealDouble(), 1e-6 );
		assertEquals( imag, converted.getImaginaryDouble(), 1e-6 );
		assertEquals( imag, access.getImaginaryDouble(), 1e-6 );
	}

	@Test
	public void testUnsignedByte()
	{
		UnsignedByteLongAccessType access = new UnsignedByteLongAccessType( 4 );
		UnsignedByteType converted = convert( access, new UnsignedByteLongAccessTypeUnsignedByteTypeConverter() );
		byte value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testUnsignedShort()
	{
		UnsignedShortLongAccessType access = new UnsignedShortLongAccessType( 4 );
		UnsignedShortType converted = convert( access, new UnsignedShortLongAccessTypeUnsignedShortTypeConverter() );
		short value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testUnsignedInteger()
	{
		UnsignedIntLongAccessType access = new UnsignedIntLongAccessType( 4 );
		UnsignedIntType converted = convert( access, new UnsignedIntLongAccessTypeUnsignedIntTypeConverter() );
		int value = 5;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

	@Test
	public void testUnsignedLong()
	{
		UnsignedLongLongAccessType access = new UnsignedLongLongAccessType( 4L );
		UnsignedLongType converted = convert( access, new UnsignedLongLongAccessTypeUnsignedLongTypeConverter() );
		long value = 5L;
		converted.set( value );
		assertEquals( value, converted.get() );
		assertEquals( value, access.get() );
	}

}
