

package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.common.ExitCodes;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.domain.CompoundLabel;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.ServiceInstance;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * This class defines the listing Service Instances command.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ListServiceInstanceCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;
	private CompoundLabel serviceType;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
        String label = OptionUtil.getOptionValue(optionSet, Options.SUPPLEMENT, true);

        String[] parts = null;
        try {
            if(CompoundLabel.isCompound(label)) {
                parts = CompoundLabel.labelComponents(label);
                this.serviceType = new CompoundLabel(parts[0], parts[1], parts[2]);
            } else
                this.serviceType = new CompoundLabel(label);
        } catch(IllegalArgumentException iae) {
            throw new ExecutionException(DisplayUtil.INVALID_ARGUMENT +iae.getMessage(),
                                         ExitCodes.INVALID_ARGS.getExitCode());
        }

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
