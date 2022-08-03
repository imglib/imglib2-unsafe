package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.NativeBooleanUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link NativeBooleanUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class NativeBooleanUnsafeTest
{

	private NativeBooleanUnsafe b;

	private long address;

	private final Boolean startingValue = false;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		b = new NativeBooleanUnsafe( address );
		b.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( b.getAddres() );
	}

	@Test
	public void testNativeBooleanUnsafeGetValue()
	{
		assertEquals( startingValue, b.getValue( 0 ) );
	}

	@Test
	public void testNativeBooleanUnsafeSetValue()
	{
		boolean newValue = true;
		b.setValue( 0, newValue );
		assertEquals( newValue, b.getValue( 0 ) );
	}

	@Test
	public void testNativeBooleanUnsafeGetAddress()
	{
		assertEquals( address, b.getAddres() );
	}

}
