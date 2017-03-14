/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2016 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
 * John Bogovic, Albert Cardona, Barry DeZonia, Christian Dietz, Jan Funke,
 * Aivar Grislis, Jonathan Hale, Grant Harris, Stefan Helfrich, Mark Hiner,
 * Martin Horn, Steffen Jaensch, Lee Kamentsky, Larry Lindsey, Melissa Linkert,
 * Mark Longair, Brian Northan, Nick Perry, Curtis Rueden, Johannes Schindelin,
 * Jean-Yves Tinevez and Michael Zinsmaier.
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

package net.imglib2.img.unsafe;

import net.imglib2.AbstractLocalizingCursor;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.util.IntervalIndexer;

/**
 * Localizing {@link Cursor} on an {@link UnsafeImg}.
 *
 * @param <T>
 *
 * @author Stephan Preibisch
 * @author Stephan Saalfeld
 * @author Christian Dietz
 * @author Tobias Pietzsch
 *
 */
//TODO Review Javadoc
public abstract class AbstractUnsafeLocalizingCursor< T extends NativeLongAccessType< T > > extends AbstractLocalizingCursor< T >
{

	/**
	 * Size of this cursor.
	 */
	protected final long size;

	/**
	 * Offset of this cursor.
	 */
	protected final long offset;

	/**
	 * An instance of T.
	 */
	protected final T type;

	/**
	 * The underlying source {@link UnsafeImg}.
	 */
	protected final UnsafeImg< T, ? > img;

	/**
	 * Last index.
	 */
	protected final Long lastIndex;

	/**
	 * Maximum of the {@link UnsafeImg} in every dimension. This is used to check
	 * isOutOfBounds().
	 */
	protected final int[] max;

	/**
	 * TODO Javadoc
	 *
	 * @param cursor
	 */
	protected AbstractUnsafeLocalizingCursor( final AbstractUnsafeLocalizingCursor< T > cursor )
	{
		super( cursor.numDimensions() );

		this.img = cursor.img;
		this.type = img.createLinkedType();
		this.offset = cursor.offset;
		this.size = cursor.size;

		this.lastIndex = offset + size - 1;

		max = new int[ n ];
		for ( int d = 0; d < n; ++d )
		{
			position[ d ] = cursor.position[ d ];
			max[ d ] = cursor.max[ d ];
		}

		type.updateIndex( cursor.type.getIndexLong() );
		type.updateContainer( this );

		reset();
	}

	/**
	 * TODO Javadoc
	 *
	 * @param img
	 * @param offset
	 * @param size
	 */
	public AbstractUnsafeLocalizingCursor( final UnsafeImg< T, ? > img, final long offset, final long size )
	{
		super( img.numDimensions() );

		this.img = img;
		this.offset = offset;
		this.size = size;

		this.type = img.createLinkedType();
		this.lastIndex = offset + size - 1;

		max = new int[ n ];
		for ( int d = 0; d < n; ++d )
			max[ d ] = ( int ) img.max( d );

		reset();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get()
	{
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext()
	{
		return type.getIndexLong() < lastIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fwd()
	{
		type.incIndex();

//		 for ( int d = 0; d < n; ++d )
//		 {
//		 if ( ++position[ d ] > max[ d ] ) position[ d ] = 0;
//		 else break;
//		 }

		/*
		 * Benchmarks @ 2014-04-01 demonstrate that the less readable code below
		 * is reliably 5-10% faster than the almost equivalent commented code
		 * above. The reason is NOT simply that d=0 is executed outside the
		 * loop. We have tested that and it does not provide improved speed when
		 * done in the above version of the code. Below, it plays a role.
		 */
		if ( ++position[ 0 ] <= max[ 0 ] )
			return;
		else
		{
			position[ 0 ] = 0;

			for ( int d = 1; d < n; ++d )
				if ( ++position[ d ] <= max[ d ] )
					break;
				else
					position[ d ] = 0;

			return;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void jumpFwd( final long steps )
	{
		type.incIndex( ( int ) steps );
		IntervalIndexer.indexToPosition( type.getIndexLong(), img.dim, position );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset()
	{
		type.updateIndex( offset - 1 );

		IntervalIndexer.indexToPosition( offset, img.dim, position );
		position[ 0 ]--;

		type.updateContainer( this );
	}
}
