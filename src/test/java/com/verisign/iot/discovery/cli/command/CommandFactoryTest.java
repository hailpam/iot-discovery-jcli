
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.Command;
import com.verisign.iot.discovery.cli.CommandFactory;
import com.verisign.iot.discovery.cli.TestUtils;
import com.verisign.iot.discovery.cli.parser.DefaultOptionParser;
import joptsimple.OptionSet;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author nbrasey
 * @version 1.0 May 06, 2015
 */
public class CommandFactoryTest {

	@Test
	public void testDnsSecCommand () throws Exception {
		Command command = buildCommand( "-c" );
		assertTrue( command instanceof CheckDnsSecCommand );
	}


	@Test
	public void testServiceInstanceCommand () throws Exception {
		Command command = buildCommand( "-i -d domain -s serviceType" );
		assertTrue( command instanceof ListServiceInstanceCommand );
	}


	@Test
	public void testServiceTypesCommand () throws Exception {
		Command command = buildCommand( "-l -d domain" );
		assertTrue( command instanceof ListServiceTypesCommand );
	}


	@Test
	public void testTxtRecordCommand () throws Exception {
		Command command = buildCommand( "-t TEXT_LABEL -d domain -s serviceType" );
		assertTrue( command instanceof ListTextRecordCommand );
	}


	private Command buildCommand ( String arg ) throws Exception {
		String[] args = TestUtils.getArgs( arg );
		DefaultOptionParser parser = new DefaultOptionParser();

		OptionSet optionSet = parser.parse( args );

		return CommandFactory.buildCommand( optionSet );

	}

}
