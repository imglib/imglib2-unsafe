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
package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.img.basictypeaccess.volatiles.VolatileBooleanAccess;
import net.imglib2.img.basictypelongaccess.BooleanLongAccess;

public class NativeBooleanUnsafe
		implements BooleanLongAccess, VolatileBooleanAccess
{
	private static final boolean DEFAULT_IS_VALID = true;
	private static final byte ZERO = (byte) 0;
	private static final byte ONE = (byte) 1;

	private final ByteUnsafe wrapped;

	public NativeBooleanUnsafe( final long address )
	{
		this( address, null );
	}

	public NativeBooleanUnsafe( final long address, final boolean isValid )
	{
		this( address, null, isValid );
	}

	public NativeBooleanUnsafe( final long address, final Object ownerReference )
	{
		this( address, ownerReference, DEFAULT_IS_VALID );
	}

	public NativeBooleanUnsafe( final long address, final Object ownerReference, final boolean isValid )
	{
		super();
		this.wrapped = new ByteUnsafe(address, ownerReference, isValid);
	}

	@Override
	public boolean getValue( final int index )
	{
		return getValue( ( long ) index );
	}

	@Override
	public void setValue( final int index, final boolean value )
	{
		setValue( ( long ) index, value );
	}

	@Override
	public boolean getValue( final long index )
	{
		return wrapped.getValue(index) > 0;
	}

	@Override
	public void setValue( final long index, final boolean value )
	{
		wrapped.setValue(index, value ? ONE : ZERO);
	}

	public long getAddres()
	{
		return wrapped.getAddres();
	}

	@Override
	public boolean isValid()
	{
		return wrapped.isValid();
	}

	@Override
	public void finalize() throws Throwable
	{
		wrapped.finalize();
	}

}
