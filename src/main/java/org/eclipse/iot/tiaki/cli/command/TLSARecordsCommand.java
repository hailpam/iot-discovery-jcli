/*
 * Copyright (c) 2015, Verisign, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package org.eclipse.iot.tiaki.cli.command;

import org.eclipse.iot.tiaki.cli.ConsoleWriter;
import org.eclipse.iot.tiaki.cli.exception.ExecutionException;
import org.eclipse.iot.tiaki.cli.exception.OptionsNotValidException;
import org.eclipse.iot.tiaki.cli.parser.Options;
import org.eclipse.iot.tiaki.cli.util.DisplayUtil;
import org.eclipse.iot.tiaki.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.CertRecord;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.TLSAPrefix;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * This class defines the listing TLSA certficates command.
 *
 */
public class TLSARecordsCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;
	private TLSAPrefix tlsaPrefix;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );
        this.domain = new Fqdn(OptionUtil.getOptionValue(optionSet, Options.SUPPLEMENT, true),
                               OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true ));

		this.tlsaPrefix = new TLSAPrefix( OptionUtil.getOptionValue( optionSet, Options.TLSA_RECORD, false ) );
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
