
package com.verisign.iot.discovery.cli.parsing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.verisign.iot.discovery.cli.TestUtils;
import com.verisign.iot.discovery.cli.exception.ParsingException;
import com.verisign.iot.discovery.cli.parser.DefaultOptionParser;

/**
 * Created by nbrasey on 5/4/15.
 */
public class ListServiceTypeParsingTest {

	@Test (expected = ParsingException.class)
	public void testRequiredArguments() throws Exception {

		String[] args = TestUtils.getArgs("-l");

		DefaultOptionParser parser = new DefaultOptionParser();
		try
		{
			parser.parse( args );
		}
		catch (Exception e)
		{
			assertTrue(e instanceof ParsingException);
			assertEquals("Missing required option(s) [d/domain]", e.getMessage());
			throw e;
		}
	}
}
