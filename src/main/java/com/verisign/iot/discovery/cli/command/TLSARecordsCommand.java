package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.common.ExitCodes;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.CertRecord;
import com.verisign.iot.discovery.domain.CompoundLabel;
import com.verisign.iot.discovery.domain.DnsCertPrefix;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * This class defines the listing TLSA certficates command.
 *
 * @author nbrasey <tmurphy@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class TLSARecordsCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;
    private String label;
	private DnsCertPrefix tlsaPrefix;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );
        this.label = OptionUtil.getOptionValue(optionSet, Options.SUPPLEMENT, true);
        if(CompoundLabel.isCompound(label))
            throw new ExecutionException(DisplayUtil.INVALID_ARGUMENT +": compound label in input",
                                         ExitCodes.INVALID_ARGS.getExitCode());
        this.domain = new Fqdn(this.label,
                               OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true ));

		this.tlsaPrefix = new DnsCertPrefix( OptionUtil.getOptionValue( optionSet, Options.TLSA_RECORD, false ) );
    }


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                                throws DnsServiceException
    {
        Set<CertRecord> records = null;
        try {
            records = this.dnsSd.listTLSARecords(this.domain,
                                                 this.tlsaPrefix, !this.insecureMode );
        } catch(LookupException le) {
            throw new DnsServiceException(le.dnsError(),
                                        String.format(DisplayUtil.map(le.dnsError()), domain.fqdn()), true);
        }
        for(CertRecord record: records){
			consoleWriter.log( record.toDisplay() );
		}
	}

}
