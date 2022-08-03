package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import net.imglib2.img.basictypelongaccess.unsafe.ShortUnsafe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link ShortUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class ShortUnsafeTest
{

	private ShortUnsafe s;

	private long address;

	private final Short startingValue = 5;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		s = new ShortUnsafe( address );
		s.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( s.getAddres() );
	}

	@Test
	public void testShortUnsafeGetValue()
	{
		assertEquals( ( long ) startingValue, s.getValue( 0 ) );
	}

	@Test
	public void testShortUnsafeSetValue()
	{
		short newValue = 6;
		s.setValue( 0, newValue );
		assertEquals( newValue, s.getValue( 0 ) );
	}

	@Test
	public void testShortUnsafeGetAddress()
	{
		assertEquals( address, s.getAddres() );
	}

}
