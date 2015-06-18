package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.commons.Constants;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import joptsimple.OptionSet;


/**
 * This class defines the DNSSEC status check command.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class CheckDnsSecCommand extends DnsSdAbstractCommand
{

    private Fqdn domain;


    @Override
    public void initialize(OptionSet optionSet) throws ExecutionException, OptionsNotValidException
    {
        super.initialize(optionSet);

        String domainStr = null;
        if (optionSet.hasArgument(Options.DNS_SEC_STATUS)) {
            domainStr = optionSet.valueOf(Options.DNS_SEC_STATUS).toString();
        }

        if (domainStr == null) {
            domainStr = Constants.DEFAULT_DNSSEC_DOMAIN;
        }

        this.domain = new Fqdn(domainStr);
    }

    @Override
    public void doExecute(ConsoleWriter consoleWriter) throws DnsServiceException
    {

        try {
            this.dnsSd.isDnsSecValid(this.domain);
            consoleWriter.log(String.format(DisplayUtil.SECURE_DNS_RESPONSE,
                                            this.domain.domain()));
        } catch (LookupException e) {
            throw new DnsServiceException(e.dnsError(),
                                          String.format(DisplayUtil.map(e.dnsError()), domain.fqdn()),
                                          true);
        }
    }

}
