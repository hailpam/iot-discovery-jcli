# IoT Discovery Services CLI Application

## Intro

### DNS-SD, https://tools.ietf.org/html/rfc6763
DNS-SD (DNS-based Service Discovery) is an IETF standard based on the RF6763.

### Purpose
This project aims at providing a comprehensive command line tool to lookup services provisioned according to DNS-SD. The community is invited to use and contribute to this tool.

The command line tool makes use of the Verisign' Discovery Services APIs (https://github.com/verisign/iot-discovery-services).

## Build Process
This is a Gradle project, so pretty intuitive to build up. Hereafter a simple example on how to get started in building it.

```
$ cd $PROJECT_HOME
$ gradle clean fatJar
```

## Usage
```
$cd $PROJECT_HOME
$ java -jar build/libs/iot-discovery-jcli-1.0.jar

$ jcli -h
Usage: java -jar iot-discovery-jcli-1.0.jar [<command>[<arg>]] [options]
Commands:
  -h, --help                              	Display this usage and quit.            
  -i, --list-instances                    	Detailed display of service instances; -s and -d are required.
  -l, --list-services                     	Display the service types; -d is required.
  -c [domain], --dnssec-status [domain]   	Check the DNSSEC status of 'domain'; if not specified, check against the default one.
  -x [port:protocol], --tlsa [port:protocol]	Display the TLSA records referring to the couple 'port:protocol' (default ones if not specified); -d and -s (which has only the required 'label') are required.
  -t <label>, --text-record <label>       	Display the text records having 'label'; -d is required.
Options:
  -e, --insecure                          	Inhibit the DNSSEC validation upon lookup.
  -v, --verbose                           	Display a verbose output of the resolution.
  -n <resolvers>, --servers <resolvers>   	Comma-separated list of resolver servers, overriding the default ones.
  -u <filename>, --trust-anchor <filename>	Specify the file containing the trust anchor.
  -d <domain>, --domain <domain>          	Specify the domain name to use upon resolution process.
  -s <label>, --supplement <label>        	Specify a supplementary 'label' to concatenate/use to query for; the 'label' has to follow the pattern: 'label[:sublabel:proto>|:proto]', e.g. 'http:printer:tcp', or only 'http', or 'http:tcp'.
```

## Examples of Use
####List all service types from dns-sd.org
Please note, the "-e" is required here as dns-sd.org is not secured with DNSSEC.
```
$ cd $PROJECT_HOME
$ java -jar ./build/libs/iot-discovery-jcli-1.0.jar -d dns-sd.org -e
afpovertcp
ftp
http
ipp
pdl-datastream
printer
ssh
```


####List all service instances of type 'ftp'
```
$ java -jar iot-discovery-jcli-1.0.jar -d dns-sd.org -i -s ftp -e
"apple quicktime files" ftp.apple.com TCP:21 "txtvers=1" "path=/quicktime"
"microsoft developer files" ftp.microsoft.com TCP:21 "txtvers=1" "path=/developer"
"restricted, registered users only" pretend-server.dns-sd.org TCP:21 "txtvers=1" "path=/"
```
The output shows a service description ("apple quicktime files"), followed by the url, protocol and port (ftp.apple.com TCP:21), and additional information (stored in a TXT record).

####Use a specific resolver 
```
$ java -jar iot-discovery-jcli-1.0.jar -d mcn366rzmd2a.1.iotverisign.com -l -n 64.6.64.6
coap
mqtt
```
The '-n' can be used to specify which DNS resolver should be used. This is useful to choose a DNSSEC-enabled resolver, when the default resolver is not.

# License
Eclipse Public License - v 1.0
=======
https://www.eclipse.org/legal/epl-v10.html
