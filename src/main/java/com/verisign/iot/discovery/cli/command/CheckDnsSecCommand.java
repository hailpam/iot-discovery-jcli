package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.commons.Constants;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 5/4/15.
 */
public class CheckDnsSecCommand extends DnsSdAbstractCommand {

    private Fqdn domain;

    @Override
    public void initialize(OptionSet optionSet) throws OptionsNotValidException {
        super.initialize(optionSet);

        String domainStr = null;
        if (optionSet.hasArgument(Options.DNS_SEC_STATUS)) {
            domainStr = optionSet.valueOf(Options.DNS_SEC_STATUS).toString();
        }

        if (domainStr == null) {
            domainStr = Constants.DEFAULT_DNSSEC_DOMAIN;
        }

        this.domain = new Fqdn("", domainStr);
    }

    @Override
    public void doExecute(ConsoleWriter consoleWriter) throws DnsServiceException {

        try {
            this.dnsSd.isDnsSecValid(this.domain);
            consoleWriter.log(String.format("DNSSEC status check for domain [%s] successful.", this.domain.domain()));
        } catch (LookupException e) {
            StatusCode statusCode = e.dnsError();

            if (statusCode == StatusCode.RESOURCE_INSECURE_ERROR) {
                consoleWriter.log(String.format("Failed DNSSEC validation for domain [%s]", this.domain.domain()));
            }
            else {
                throw e;
            }
        }
    }
}
