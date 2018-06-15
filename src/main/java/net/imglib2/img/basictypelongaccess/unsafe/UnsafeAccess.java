package net.imglib2.img.basictypelongaccess.unsafe;

import net.imglib2.type.PrimitiveType;

public interface UnsafeAccess< A extends UnsafeAccess< A > >
{

	A createAccess( long numEntities );

	PrimitiveType getType();

	long getAddress();

	long getSize();

}
