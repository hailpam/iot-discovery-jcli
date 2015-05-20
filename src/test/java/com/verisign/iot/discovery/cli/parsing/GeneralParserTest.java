
package com.verisign.iot.discovery.cli.parsing;

import com.verisign.iot.discovery.cli.TestUtils;
import com.verisign.iot.discovery.cli.exception.ParsingException;
import com.verisign.iot.discovery.cli.parser.DefaultOptionParser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Created by nbrasey on 5/4/15.
 */
public class GeneralParserTest {

	@Test (expected = ParsingException.class)
	public void testUnknownArguments () throws Exception {

		String[] args = TestUtils.getArgs("-p");

		DefaultOptionParser parser = new DefaultOptionParser();
		try
		{
			parser.parse( args );
		}
		catch (Exception e)
		{
			assertTrue(e instanceof ParsingException);
			assertEquals("invalid option -- 'p'", e.getMessage());
			throw e;
		}
	}

	@Test (expected = ParsingException.class)
	public void testMissingArguments () throws Exception {

		// The -i argument requires also the -d and -s
		String[] args = TestUtils.getArgs( "-i" );

		DefaultOptionParser parser = new DefaultOptionParser();
		try
		{
			parser.parse( args );
		}
		catch (Exception e)
		{
			assertTrue(e instanceof ParsingException);
//			assertEquals(e.getMessage(), "Missing required option(s) [s/service, d/domain]");
			throw e;
		}
	}

}
