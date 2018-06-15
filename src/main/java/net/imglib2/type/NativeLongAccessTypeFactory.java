/*-
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2018 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
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
package net.imglib2.type;

import java.util.function.Function;

import net.imglib2.img.NativeImg;
import net.imglib2.img.NativeLongAccessImg;
import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.CharLongAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;

/**
 * {@code NativeTypeFactory} is used to {@link #createLinkedType(NativeImg)
 * create} a linked type {@code T} for a matching {@link NativeImg}.
 * <p>
 * It also {@link #getPrimitiveType() provides} information about the primitive
 * java type that backs the type {@code T}.
 * <p>
 * {@code NativeTypeFactory} binds a {code PrimitiveType} enum constant to a
 * {@code Access} interface ({@code ByteAccess}, {@code DoubleAccess}, and so
 * on). Instances can only be constructed via static methods
 * {@link #BYTE(Function)}, {@link #DOUBLE(Function)}, etc. to prevent
 * non-matching combinations of {@code Access} interface and
 * {@link PrimitiveType} constant.
 *
 * @param <T>
 *            the {@link NativeType} this is attached to
 * @param <A>
 *            the {@code Access} family ({@code ByteAccess},
 *            {@code DoubleAccess}, and so on)
 *
 * @author Tobias Pietzsch
 */
public final class NativeLongAccessTypeFactory< T extends NativeLongAccessType< T >, A >
{
	private final PrimitiveType primitiveType;

	private final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType;

	/**
	 * @param primitiveType
	 *            the {@link PrimitiveType} enum constant matching {@code A}.
	 * @param createLinkedType
	 *            given a matching {@link NativeLongAccessImg} creates a linked
	 *            {@link NativeType} {@code T}.
	 */
	private NativeLongAccessTypeFactory(
			final PrimitiveType primitiveType,
			final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		this.primitiveType = primitiveType;
		this.createLinkedType = createLinkedType;
	}

	/**
	 * Get the primitive java type that backs the {@code NativeType T}.
	 *
	 * @return primitive java type that backs {@code T}
	 */
	public PrimitiveType getPrimitiveType()
	{
		return primitiveType;
	}

	/**
	 * Creates a new {@code T} instance which is linked to {@code img}. This
	 * means that the instance will ask {@code img} for the {@code Access} that
	 * stores the pixel data.
	 *
	 * @param img
	 *            a matching {@link NativeLongAccessImg}
	 * @return a new {@code T} that is linked to on {@code img}.
	 */
	public T createLinkedType( final NativeLongAccessImg< T, ? extends A > img )
	{
		return createLinkedType.apply( img );
	}

	public static < T extends NativeLongAccessType< T >, A extends ByteLongAccess > NativeLongAccessTypeFactory< T, A > BYTE( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.BYTE, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends CharLongAccess > NativeLongAccessTypeFactory< T, A > CHAR( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.CHAR, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends ShortLongAccess > NativeLongAccessTypeFactory< T, A > SHORT( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.SHORT, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends IntLongAccess > NativeLongAccessTypeFactory< T, A > INT( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.INT, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends LongLongAccess > NativeLongAccessTypeFactory< T, A > LONG( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.LONG, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends FloatLongAccess > NativeLongAccessTypeFactory< T, A > FLOAT( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.FLOAT, createLinkedType );
	}

	public static < T extends NativeLongAccessType< T >, A extends DoubleLongAccess > NativeLongAccessTypeFactory< T, A > DOUBLE( final Function< NativeLongAccessImg< T, ? extends A >, T > createLinkedType )
	{
		return new NativeLongAccessTypeFactory<>( PrimitiveType.DOUBLE, createLinkedType );
	}
}
