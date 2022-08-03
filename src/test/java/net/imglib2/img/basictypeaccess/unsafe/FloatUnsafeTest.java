package net.imglib2.img.basictypeaccess.unsafe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.imglib2.img.basictypelongaccess.unsafe.FloatUnsafe;
import net.imglib2.img.basictypelongaccess.unsafe.UnsafeUtil;

/**
 * Tests basic {@link FloatUnsafe} functionality
 * 
 * @author Gabriel Selzer
 */
public class FloatUnsafeTest
{

	private FloatUnsafe f;

	private long address;

	private final Float startingValue = 5f;

	@Before
	public void setup()
	{
		// Allocate heap memory for testing
		address = UnsafeUtil.UNSAFE.allocateMemory( 8 );

		f = new FloatUnsafe( address );
		f.setValue( 0, startingValue );
	}

	@After
	public void tearDown()
	{
		// Free heap memory
		UnsafeUtil.UNSAFE.freeMemory( f.getAddres() );
	}

	@Test
	public void testFloatUnsafeGetValue()
	{
		assertEquals( startingValue, f.getValue( 0 ), 1e-6 );
	}

	@Test
	public void testFloatUnsafeSetValue()
	{
		float newValue = 6f;
		f.setValue( 0, newValue );
		assertEquals( newValue, f.getValue( 0 ), 1e-6 );
	}

	@Test
	public void testFloatUnsafeGetAddress()
	{
		assertEquals( address, f.getAddres() );
	}

}
