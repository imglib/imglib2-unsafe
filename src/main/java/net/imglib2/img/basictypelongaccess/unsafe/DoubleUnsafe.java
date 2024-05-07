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

import net.imglib2.img.basictypeaccess.volatiles.VolatileDoubleAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;

public class DoubleUnsafe extends AbstractStridedUnsafeLongAccess implements DoubleLongAccess, VolatileDoubleAccess
{

	private final long address;

	private final Object ownerReference;

	public DoubleUnsafe( final long address )
	{
		this( address, null );
	}

	public DoubleUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public DoubleUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public DoubleUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super( Double.BYTES, isValid );
		this.address = address;
		this.ownerReference = ownerReference;
	}

	@Override
	public double getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final double value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public double getValue( final long index )
	{
		return UnsafeUtil.UNSAFE.getDouble( getPosition( address, index ) );
	}

	@Override
	public void setValue( final long index, final double value )
	{
		UnsafeUtil.UNSAFE.putDouble( getPosition( address, index ), value );
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
