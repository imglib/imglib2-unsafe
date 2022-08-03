package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import net.imglib2.img.basictypelongaccess.unsafe.CharUnsafe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link CharUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class CharUnsafeTest
{

	private CharUnsafe c;

	private long address;

	private final Character startingValue = 'a';

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		c = new CharUnsafe( address );
		c.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( c.getAddres() );
	}

	@Test
	public void testCharUnsafeGetValue()
	{
		assertEquals( ( long ) startingValue, c.getValue( 0 ) );
	}

	@Test
	public void testCharUnsafeSetValue()
	{
		char newValue = 'b';
		c.setValue( 0, newValue );
		assertEquals( newValue, c.getValue( 0 ) );
	}

	@Test
	public void testCharUnsafeGetAddress()
	{
		assertEquals( address, c.getAddres() );
	}

}
