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

import static net.imglib2.img.basictypeaccess.AccessFlags.DIRTY;
import static net.imglib2.img.basictypeaccess.AccessFlags.VOLATILE;

import java.util.Set;

import net.imglib2.img.basictypeaccess.AccessFlags;
import net.imglib2.img.basictypeaccess.array.ArrayDataAccess;
import net.imglib2.img.basictypeaccess.volatiles.array.DirtyVolatileByteArray;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningCharUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningDoubleUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningFloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningIntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningLongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.owning.OwningShortUnsafe;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.NativeLongAccessTypeFactory;
import net.imglib2.type.PrimitiveType;

/**
 * Given a {@link PrimitiveType} and {@link AccessFlags} creates a specific
 * {@link ArrayDataAccess}. For example, {@code BYTE} with flags {@code DIRTY}
 * and {@code VOLATILE} specifies {@link DirtyVolatileByteArray}.
 *
 * @author Tobias Pietzsch
 */
public class UnsafeAccessFactory
{
	public static < T extends NativeLongAccessType< T >, A > A get(
			final T type )
	{
		return get( type, AccessFlags.setOf() );
	}

	public static < T extends NativeLongAccessType< T >, A > A get(
			final T type,
			final Set< AccessFlags > flags )
	{
		return get( type.getNativeLongAccessTypeFactory().getPrimitiveType(), flags );
	}

	public static < A extends UnsafeAccess< A > > A get(
			final NativeLongAccessTypeFactory< ?, ? super A > typeFactory )
	{
		return get( typeFactory.getPrimitiveType(), AccessFlags.setOf() );
	}

	public static < A extends UnsafeAccess< A > > A get(
			final NativeLongAccessTypeFactory< ?, ? super A > typeFactory,
			final Set< AccessFlags > flags )
	{
		return get( typeFactory.getPrimitiveType(), flags );
	}

	@SuppressWarnings( "unchecked" )
	public static < A extends UnsafeAccess< A > > A get(
			final PrimitiveType primitiveType,
			final Set< AccessFlags > flags )
	{
		final boolean dirty = flags.contains( DIRTY );
		final boolean volatil = flags.contains( VOLATILE );
		switch ( primitiveType )
		{
		case BYTE:
			return dirty
					? volatil
							? ( A ) new OwningByteUnsafe( 0 )
							: ( A ) new OwningByteUnsafe( 0 )
					: volatil
							? ( A ) new OwningByteUnsafe( 0 )
							: ( A ) new OwningByteUnsafe( 0 );
		case CHAR:
			return dirty
					? volatil
							? ( A ) new OwningCharUnsafe( 0 )
							: ( A ) new OwningCharUnsafe( 0 )
					: volatil
							? ( A ) new OwningCharUnsafe( 0 )
							: ( A ) new OwningCharUnsafe( 0 );
		case DOUBLE:
			return dirty
					? volatil
							? ( A ) new OwningDoubleUnsafe( 0 )
							: ( A ) new OwningDoubleUnsafe( 0 )
					: volatil
							? ( A ) new OwningDoubleUnsafe( 0 )
							: ( A ) new OwningDoubleUnsafe( 0 );
		case FLOAT:
			return dirty
					? volatil
							? ( A ) new OwningFloatUnsafe( 0 )
							: ( A ) new OwningFloatUnsafe( 0 )
					: volatil
							? ( A ) new OwningFloatUnsafe( 0 )
							: ( A ) new OwningFloatUnsafe( 0 );
		case INT:
			return dirty
					? volatil
							? ( A ) new OwningIntUnsafe( 0 )
							: ( A ) new OwningIntUnsafe( 0 )
					: volatil
							? ( A ) new OwningIntUnsafe( 0 )
							: ( A ) new OwningIntUnsafe( 0 );
		case LONG:
			return dirty
					? volatil
							? ( A ) new OwningLongUnsafe( 0 )
							: ( A ) new OwningLongUnsafe( 0 )
					: volatil
							? ( A ) new OwningLongUnsafe( 0 )
							: ( A ) new OwningLongUnsafe( 0 );
		case SHORT:
			return dirty
					? volatil
							? ( A ) new OwningShortUnsafe( 0 )
							: ( A ) new OwningShortUnsafe( 0 )
					: volatil
							? ( A ) new OwningShortUnsafe( 0 )
							: ( A ) new OwningShortUnsafe( 0 );
		default:
			throw new IllegalArgumentException();
		}
	}
}
