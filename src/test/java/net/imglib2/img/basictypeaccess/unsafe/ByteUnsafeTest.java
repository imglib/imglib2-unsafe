package net.imglib2.img.basictypeaccess.unsafe;

import net.imglib2.img.basictypelongaccess.unsafe.ByteUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests basic {@link ByteUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class ByteUnsafeTest
{

	private ByteUnsafe b;

	private long address;

	private final Byte startingValue = 5;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		b = new ByteUnsafe( address );
		b.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( b.getAddres() );
	}

	@Test
	public void testByteUnsafeGetValue()
	{
		assertEquals( ( long ) startingValue, b.getValue( 0 ) );
	}

	@Test
	public void testByteUnsafeSetValue()
	{
		byte newValue = 6;
		b.setValue( 0, newValue );
		assertEquals( newValue, b.getValue( 0 ) );
	}

	@Test
	public void testByteUnsafeGetAddress()
	{
		assertEquals( address, b.getAddres() );
	}

}
