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
package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileFloatAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;

public class FloatUnsafe extends AbstractStridedUnsafeLongAccess implements FloatLongAccess, VolatileFloatAccess
{

	private final long address;

	private final Object ownerReference;

	public FloatUnsafe( final long address )
	{
		this( address, null );
	}

	public FloatUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public FloatUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public FloatUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Float.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public float getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final float value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public float getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getFloat( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final float value )
	{
		UnsafeUtil.UNSAFE.putFloat( getPosition( address, index ), value );
	}

	public long getAddres()
	{
		return address;
	}

	@Override
	public void finalize() throws Throwable
	{
		try
		{
			if ( this.ownerReference instanceof Runnable )
				( ( Runnable ) ownerReference ).run();
		}
		finally
		{
			super.finalize();
		}
	}

}
