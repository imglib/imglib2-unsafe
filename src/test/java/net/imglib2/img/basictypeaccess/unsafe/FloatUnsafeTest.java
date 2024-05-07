/*-
 * #%L
 * ImgLib2 data structures using Unsafe.
 * %%
 * Copyright (C) 2017 - 2024 Howard Hughes Medical Institute.
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
package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.FloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link FloatUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class FloatUnsafeTest
{

	private FloatUnsafe f;

	private long address;

	private final Float startingValue = 5f;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		f = new FloatUnsafe( address );
		f.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( f.getAddres() );
	}

	@Test
	public void testFloatUnsafeGetValue()
	{
		assertEquals( startingValue, f.getValue( 0 ), 1e-6 );
	}

	@Test
	public void testFloatUnsafeSetValue()
	{
		float newValue = 6f;
		f.setValue( 0, newValue );
		assertEquals( newValue, f.getValue( 0 ), 1e-6 );
	}

	@Test
	public void testFloatUnsafeGetAddress()
	{
		assertEquals( address, f.getAddres() );
	}

}
