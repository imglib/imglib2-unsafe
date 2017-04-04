package net.imglib2.img.basictypelongaccess.unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import sun.misc.Unsafe;

public class UnsafeUtil
{

	public static final Unsafe UNSAFE;
	static
	{
		try
		{
			final PrivilegedExceptionAction< Unsafe > action = () -> {
				final Field field = Unsafe.class.getDeclaredField( "theUnsafe" );
				field.setAccessible( true );
				return ( Unsafe ) field.get( null );
			};

			UNSAFE = AccessController.doPrivileged( action );
		}
		catch ( final Exception ex )
		{
			throw new RuntimeException( ex );
		}
	}

	public static final long getAddress( final Object store )
	{
		final Object[] array = new Object[] { store };

		final long baseOffset = UNSAFE.arrayBaseOffset( Object[].class );
		final int addressSize = UNSAFE.addressSize();
		long objectAddress;
		switch ( addressSize )
		{
		case 4:
			objectAddress = UNSAFE.getInt( array, baseOffset );
			break;
		case 8:
			objectAddress = UNSAFE.getLong( array, baseOffset );
			break;
		default:
			throw new Error( "unsupported address size: " + addressSize );
		}

		return objectAddress;
	}

	public static long getFirstArrayElementAddress( final Object array )
	{
		final long arrayAddress = getAddress( array );
		final long baseOffset = UNSAFE.arrayBaseOffset( array.getClass() );
		return arrayAddress + baseOffset;
//		final int addressSize = UNSAFE.addressSize();
//		long objectAddress;
//		final long off = 4l;
//		switch ( addressSize )
//		{
//		case 4:
//			objectAddress = UNSAFE.getInt( array, off );
//			break;
//		case 8:
//			objectAddress = UNSAFE.getLong( array, off );
//			break;
//		default:
//			throw new Error( "unsupported address size: " + addressSize );
//		}
//
//		System.out.println( "OK? " + addressSize + " " + objectAddress );
//		return objectAddress;
	}

	public static OwningUnsafe create( final long sizeInBytes )
	{
		return new OwningUnsafe( sizeInBytes );
	}

	public static class OwningUnsafe
	{
		private long address;

		public OwningUnsafe( final long sizeInBytes )
		{
			super();
			this.address = UNSAFE.allocateMemory( sizeInBytes );
		}

		public long getAddress()
		{
			return address;
		}

		public void discard()
		{
			UNSAFE.freeMemory( address );
			this.address = 0;
		}

		public boolean isValid()
		{
			return this.address > 0;
		}

		@Override
		protected void finalize()
		{
			if ( isValid() )
				discard();
		}
	}

	public static void main( final String[] args )
	{
		final float[] array = new float[] { 1, 2, 3 };
		final long address = getFirstArrayElementAddress( array );
		System.out.println( array[ 0 ] + " " + UNSAFE.getFloat( address ) + " " + UNSAFE.getFloat( array, 24l ) );
	}
}
