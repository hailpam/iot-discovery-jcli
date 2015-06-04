
package com.verisign.iot.discovery.cli.parser;

/**
 * Created by nbrasey on 4/30/15.
 */
public final class Options {

	public static final String HELP = "help";
	public static final String HELP_S = "h";

	public static final String VERBOSE = "verbose";
	public static final String VERBOSE_S = "v";

	public static final String INSECURE = "insecure";
	public static final String INSECURE_S = "e";

	public static final String LIST_INSTANCES = "list-instances";
	public static final String LIST_INSTANCES_S = "i";

	public static final String LIST_SERVICES = "list-services";
	public static final String LIST_SERVICES_S = "l";

	public static final String SERVICE_RECORD = "service-record";
	public static final String SERVICE_RECORD_S = "r";

	public static final String TEXT_RECORD = "text-record";
	public static final String TEXT_RECORD_S = "t";

	public static final String SERVICE = "service";
	public static final String SERVICE_S = "s";

	public static final String DNS_SERVER = "server";
	public static final String DNS_SERVER_S = "n";

	public static final String DOMAIN = "domain";
	public static final String DOMAIN_S = "d";

	public static final String TRUST_ANCHOR = "trust-anchor";
	public static final String TRUST_ANCHOR_S = "u";

	public static final String DNS_SEC_STATUS = "dnssec-status";
	public static final String DNS_SEC_STATUS_S = "c";

	public static final String TLSA_RECORD = "tlsa";

	public static final String LONG_OPTION_ERROR = "unrecognized option '--%s'";
	public static final String SHORT_OPTION_ERROR = "invalid option -- '%s'";


	public static String getUsage () {

		StringBuilder sb = new StringBuilder();

		sb.append( "Usage: java -jar iot-discovery-jcli-1.0.jar [OPTIONS]" ).append( "\n" );
		sb.append( "Options:" ).append( "\n" );
		sb.append( String.format( "  %-40s\t%-40s", "-" + HELP_S + ", --" + HELP, "display this help and exit" ) ).append( "\n" );
		sb.append( String.format( "  %-40s\t%-40s", "-" + VERBOSE_S + ", --" + VERBOSE, "turn on verbose output" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + DNS_SEC_STATUS_S + ", --" + DNS_SEC_STATUS,
						"check DNSSEC status of DNS server [default server or specified by --server]" ) ).append( "\n" );
		sb.append(
				String
						.format( "  %-40s\t%-40s", "-" + DOMAIN_S + " DOMAIN, --" + DOMAIN + " DOMAIN",
								"domain name to query/check DNSSEC status [optional if -c, --dnssec-status is specified, otherwise it is required.]" ) )
				.append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + INSECURE_S + ", --" + INSECURE,
						"do not perform DNSSEC validation of DOMAIN [optional]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "", "DNSSEC validation can also be disabled by setting INSECURE=1 in environment" ) )
				.append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + LIST_INSTANCES_S + ", --" + LIST_INSTANCES,
						"list details of service instances [--service/--domain is required]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + LIST_SERVICES_S + ", --" + LIST_SERVICES,
						"list details of services [--domain is required]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + DNS_SERVER_S + " SERVER, --" + DNS_SERVER + " SERVER",
						"DNS server to override default DNS server [optional]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + SERVICE_RECORD_S + ", --" + SERVICE_RECORD,
						"get service record [--service is required]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + SERVICE_S + " SERVICENAME, --" + SERVICE + " SERVICENAME",
						"service name to query" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + TEXT_RECORD_S + " TXTLABEL, --" + TEXT_RECORD + " TXTLABEL", "get text record" ) )
				.append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "-" + TRUST_ANCHOR_S + ", --" + TRUST_ANCHOR,
						"specify file containing trust anchor keys [optional]" ) ).append( "\n" );
		sb.append(
				String.format( "  %-40s\t%-40s", "--" + TLSA_RECORD,
						"query for tlsa record certificate data" ) ).append( "\n" );
		sb.append( "\n" );

		return sb.toString();

	}


	private Options () {
		throw new AssertionError( String.format( "Class %s not instantiable", this.getClass().getName() ) );
	}
}
