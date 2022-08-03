package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.LongUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link LongUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class LongUnsafeTest
{

	private LongUnsafe l;

	private long address;

	private final Long startingValue = 5L;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		l = new LongUnsafe( address );
		l.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( l.getAddres() );
	}

	@Test
	public void testLongUnsafeGetValue()
	{
		assertEquals( ( long ) startingValue, l.getValue( 0 ) );
	}

	@Test
	public void testLongUnsafeSetValue()
	{
		long newValue = 6L;
		l.setValue( 0, newValue );
		assertEquals( ( long ) newValue, l.getValue( 0 ) );
	}

	@Test
	public void testLongUnsafeGetAddress()
	{
		assertEquals( address, l.getAddres() );
	}

}
