/*
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

package net.imglib2.img;

import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.Fraction;

/**
 * TODO
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 * @author Philipp Hanslovsky
 */
public abstract class AbstractNativeLongAccessImg< T extends NativeLongAccessType< T >, A >
		extends AbstractImg< T >
		implements NativeLongAccessImg< T, A >
{
	final protected Fraction entitiesPerPixel;

	protected long numEntities;

	protected T linkedType;

	public AbstractNativeLongAccessImg( final long[] dim, final Fraction entitiesPerPixel )
	{
		super( dim );
		this.entitiesPerPixel = entitiesPerPixel;
		this.numEntities = entitiesPerPixel.mulCeil( numPixels );
	}

	@Override
	public void setLinkedType( final T type )
	{
		this.linkedType = type;
	}

	@Override
	public T createLinkedType()
	{
		try
		{
			return linkedType.duplicateTypeOnSameNativeImg();
		}
		catch ( final NullPointerException e )
		{
			return null;
		}
	}
}
