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
import org.eclipse.iot.tiaki.cli.common.ExitCodes;
import org.eclipse.iot.tiaki.cli.exception.ExecutionException;
import org.eclipse.iot.tiaki.cli.exception.OptionsNotValidException;
import org.eclipse.iot.tiaki.cli.parser.Options;
import org.eclipse.iot.tiaki.cli.util.DisplayUtil;
import org.eclipse.iot.tiaki.cli.util.OptionUtil;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.ServiceInstance;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * This class defines the listing Service Instances command.
 *
 */
public class ListServiceInstanceCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;
	private String serviceType;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue(optionSet, Options.DOMAIN, true);
        this.serviceType = OptionUtil.getOptionValue(optionSet, Options.SUPPLEMENT, true);
        try {
            this.domain = new Fqdn(domainStr);
        } catch(IllegalArgumentException iae) {
            throw new ExecutionException(DisplayUtil.map(StatusCode.ILLEGAL_FQDN),
                                         ExitCodes.INVALID_FQDN.getExitCode());
        }
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException
    {
        Set<ServiceInstance> serviceInstances = null;
        try {
			serviceInstances = this.dnsSd.listServiceInstances( this.domain, this.serviceType,
                                                                !super.insecureMode );
        } catch(LookupException le) {
            throw new DnsServiceException(le.dnsError(),
                                          String.format(DisplayUtil.map(le.dnsError()), domain.fqdn()),
                                          true);
        }
        for ( ServiceInstance instance : serviceInstances ) {
			consoleWriter.log( instance.toString() );
		}
	}

}
