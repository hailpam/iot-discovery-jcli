
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
public class ListServiceRecordParsingTest {

	@Test(expected = ParsingException.class)
	public void testRequiredArguments () throws Exception {

		String[] args = TestUtils.getArgs( "-r" );

		DefaultOptionParser parser = new DefaultOptionParser();
		try {
			parser.parse( args );
		}
		catch ( Exception e ) {
			assertTrue( e instanceof ParsingException );
//			assertEquals( "Missing required option(s) [s/service, d/domain]", e.getMessage() );
			throw e;
		}
	}


	@Test(expected = ParsingException.class)
	public void testRequiredDomainArguments () throws Exception {

		String[] args = TestUtils.getArgs( "-r -s serviceType" );

		DefaultOptionParser parser = new DefaultOptionParser();
		try {
			parser.parse( args );
		}
		catch ( Exception e ) {
			assertTrue( e instanceof ParsingException );
			assertEquals( "Missing required option(s) [d/domain]", e.getMessage() );
			throw e;
		}
	}


	@Test(expected = ParsingException.class)
	public void testRequiredServiceArguments () throws Exception {

		String[] args = TestUtils.getArgs( "-r -d domain" );

		DefaultOptionParser parser = new DefaultOptionParser();
		try {
			parser.parse( args );
		}
		catch ( Exception e ) {
			assertTrue( e instanceof ParsingException );
			assertEquals( "Missing required option(s) [s/supplement]", e.getMessage() );
			throw e;
		}
	}
}
