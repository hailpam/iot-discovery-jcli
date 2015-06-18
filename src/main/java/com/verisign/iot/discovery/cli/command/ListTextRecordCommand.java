
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.common.ExitCodes;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.TextRecord;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * This class defines the listing Text Records command.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ListTextRecordCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;
	private String textRecord;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );
        try {
            this.domain = new Fqdn(OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true ));
        } catch(IllegalArgumentException iae) {
            throw new ExecutionException(DisplayUtil.map(StatusCode.ILLEGAL_FQDN),
                                         ExitCodes.INVALID_FQDN.getExitCode());
        }
        this.textRecord = optionSet.valueOf( Options.TEXT_RECORD ).toString();
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException
    {
		Set<TextRecord> textRecords = null;
        try {
            textRecords = this.dnsSd.listTextRecords( this.domain, this.textRecord,
                    !super.insecureMode );
        } catch(LookupException le) {
            throw new DnsServiceException(le.dnsError(),
                                          String.format(DisplayUtil.map(le.dnsError()), domain.fqdn()),
                                          true);
        }
        for ( TextRecord textRecord : textRecords ) {
			consoleWriter.log( textRecord.toString() );
		}
	}

}
