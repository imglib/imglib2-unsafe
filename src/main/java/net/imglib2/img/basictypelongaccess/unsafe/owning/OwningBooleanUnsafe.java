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
package net.imglib2.img.basictypelongaccess.unsafe.owning;

import net.imglib2.img.basictypeaccess.volatiles.VolatileBooleanAccess;
import net.imglib2.img.basictypelongaccess.BooleanLongAccess;
import net.imglib2.img.basictypelongaccess.unsafe.BooleanUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeAccess;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import net.imglib2.type.PrimitiveType;

/**
 * A convenience wrapper for {@link BooleanUnsafe} that uses itself as the
 * owner of the memory accessed.
 * 
 * @author Gabriel Selzer
 */
public class OwningBooleanUnsafe extends AbstractOwningUnsafe implements
		BooleanLongAccess, UnsafeAccess<OwningBooleanUnsafe>,
		VolatileBooleanAccess
{
	private static final boolean DEFAULT_IS_VALID = true;

	private final BooleanUnsafe unsafe;

	private final long numEntities;

	public OwningBooleanUnsafe( final long numEntities )
	{
		this( numEntities, DEFAULT_IS_VALID );
	}

	/**
	 * The default {@link OwningBooleanUnsafe} constructor
	 * 
	 * @param numEntities
	 *            the size (in number of indices) of the backing memory.
	 * @param isValid
	 *            the validity of this {@link OwningBooleanUnsafe}
	 */
	public OwningBooleanUnsafe( final long numEntities, final boolean isValid )
	{
		super( UnsafeUtil.create( numEntities ) );
		this.unsafe = new BooleanUnsafe( owner.getAddress(), this, isValid );
		this.numEntities = numEntities;
	}

	@Override
	public boolean getValue( final int index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final int index, final boolean value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public boolean getValue( final long index )
	{
		return unsafe.getValue( index );
	}

	@Override
	public void setValue( final long index, final boolean value )
	{
		unsafe.setValue( index, value );
	}

	@Override
	public OwningBooleanUnsafe createAccess( final long numEntities )
	{
		return new OwningBooleanUnsafe( numEntities, isValid() );
	}

	@Override
	public PrimitiveType getType()
	{
		return PrimitiveType.BYTE;
	}

	@Override
	public long getSize()
	{
		return this.numEntities;
	}

	@Override
	public boolean isValid()
	{
		return unsafe.isValid();
	}

}
