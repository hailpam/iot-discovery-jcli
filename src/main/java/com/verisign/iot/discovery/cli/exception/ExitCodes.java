
package com.verisign.iot.discovery.cli.exception;

/**
 * Created by nbrasey on 5/4/15.
 */
public enum ExitCodes {

	// General errors
	GENERIC_ERROR(1, "General unexpected error"),
	// !! The error code 2 is reserved by Linux for Bash builtin errors : DO NOT USE IT HERE !!
	INVALID_ARGS(3, "Invalid argument usage"),
	UNKNOWN_DNS_SRV_HOST(4, "Unknown DNS server host"),

	// Discovery Service Errors
	LIB_CONFIGURATION_ERROR(5, "Inconsistent secured DNS settings"),
	NETWORK_ERROR(6, "Unreachable DNS or timeout expired"),
	RESOURCE_LOOKUP_ERROR(7, "Generic Lookup error"),
	RESOURCE_INSECURE_ERROR(8, "Unsecured DNS packet"),
	RESOURCE_UNEXPECTED(9, "Unexpected Resource Record Type"),
	DNS_SERVER_ERROR(10, "DNS server failure"),
	RESOLUTION_NAME_ERROR(11, "DNS name does not exist"),
	RESOLUTION_RR_TYPE_ERROR(12, "RR Type does not exist");


	int exitCode;
	String description;


	ExitCodes ( int exitCode, String description ) {
		this.exitCode = exitCode;
		this.description = description;
	}


	public int getExitCode () {
		return this.exitCode;
	}


	public String getDescription () {
		return this.description;
	}
}
