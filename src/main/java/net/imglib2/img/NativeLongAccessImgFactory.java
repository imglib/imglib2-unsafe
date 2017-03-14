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

package net.imglib2.img;

import net.imglib2.img.basictypelongaccess.ByteLongAccess;
import net.imglib2.img.basictypelongaccess.CharLongAccess;
import net.imglib2.img.basictypelongaccess.DoubleLongAccess;
import net.imglib2.img.basictypelongaccess.FloatLongAccess;
import net.imglib2.img.basictypelongaccess.IntLongAccess;
import net.imglib2.img.basictypelongaccess.LongLongAccess;
import net.imglib2.img.basictypelongaccess.ShortLongAccess;
import net.imglib2.type.NativeLongAccessType;
import net.imglib2.type.Type;
import net.imglib2.util.Fraction;

/**
 * TODO
 * 
 */
public abstract class NativeLongAccessImgFactory< T extends NativeLongAccessType< T > > extends ImgFactory< T >
{
	/**
	 * This class will ask the {@link Type} to create a suitable {@link Img} for
	 * the {@link Type} and the dimensionality.
	 * 
	 * {@link Type} will then call one of the abstract methods defined below to
	 * create the {@link NativeLongAccessImg}
	 * 
	 * @return {@link Img} - the instantiated Container
	 */
	@Override
	public NativeLongAccessImg< T, ? > create( final long[] dim, final T type )
	{
		return type.createSuitableNativeImg( this, dim );
	}

	/* basic type containers */
	public abstract NativeLongAccessImg< T, ? extends ByteLongAccess > createByteInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends CharLongAccess > createCharInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends ShortLongAccess > createShortInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends IntLongAccess > createIntInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends LongLongAccess > createLongInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends FloatLongAccess > createFloatInstance( long[] dimensions, Fraction entitiesPerPixel );

	public abstract NativeLongAccessImg< T, ? extends DoubleLongAccess > createDoubleInstance( long[] dimensions, Fraction entitiesPerPixel );
}
