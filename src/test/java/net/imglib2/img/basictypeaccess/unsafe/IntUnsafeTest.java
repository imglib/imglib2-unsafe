package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.IntUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link IntUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class IntUnsafeTest
{

	private IntUnsafe i;

	private long address;

	private final Integer startingValue = 5;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		i = new IntUnsafe( address );
		i.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( i.getAddres() );
	}

	@Test
	public void testIntUnsafeGetValue()
	{
		assertEquals( ( long ) startingValue, i.getValue( 0 ) );
	}

	@Test
	public void testIntUnsafeSetValue()
	{
		int newValue = 6;
		i.setValue( 0, newValue );
		assertEquals( newValue, i.getValue( 0 ) );
	}

	@Test
	public void testIntUnsafeGetAddress()
	{
		assertEquals( address, i.getAddres() );
	}

}
