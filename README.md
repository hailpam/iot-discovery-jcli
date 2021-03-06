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
[pmaresca@localhost tools]$ sd-lookup -h
Usage: sd-lookup [<command>[<arg>]] [options]
Commands:
  -h, --help                              	Display this usage and quit.
  -i, --list-instances                    	Detailed display of service instances; -s and -d are required.
  -l, --list-services                     	Display the service types; -d is required.
  -c [domain], --dnssec-status [domain]   	Check the DNSSEC status of 'domain'; if not specified, check against the default one.
  -x [port:protocol], --tlsa [port:protocol]	Display the TLSA records referring to the couple 'port:protocol' (default ones if not specified); -d and -s (only with required 'label') are required.
  -t <label>, --text-record <label>       	Display the text records having 'label'; -d is required.
Options:
  -e, --insecure                          	Inhibit the DNSSEC validation upon lookup.
  -v, --verbose                           	Display a verbose output of the resolution.
  -n <resolvers>, --servers <resolvers>   	Comma-separated list of resolver servers, overriding the default ones.
  -u <filename>, --trust-anchor <filename>	Specify the file containing the trust anchor.
  -d <domain>, --domain <domain>          	Specify the domain name to use upon resolution process.
  -s <label>, --supplement <label>        	Specify a supplementary 'label' to concatenate/use to query for; the 'label' has to follow the pattern: 'label[:sublabel:proto>|:proto]', e.g. 'http:printer:tcp', or only 'http', or 'http:tcp'.

```

NOTE In order to use the binary executable 'sd-lookup', follow these steps:
* export the 'TIAKI_CLI_HOME': pointing to the JAR location, under '$PROJECT_HOME/build/libs',
* add the 'bin/' directory to your syste 'PATH': update the environment pointing to '$PROJECT_HOME/bin'.

### Alternative Usage
In case you would like to use directly the JAR:

```
cd $PROJECT_HOME
gradle clean fatJar
cd build/libs
```

and, then

```
[pmaresca@localhost libs]$ java -jar iot-discovery-jcli -h
Usage: sd-lookup [<command>[<arg>]] [options]
Commands:
  -h, --help                              	Display this usage and quit.
  -i, --list-instances                    	Detailed display of service instances; -s and -d are required.
  -l, --list-services                     	Display the service types; -d is required.
  -c [domain], --dnssec-status [domain]   	Check the DNSSEC status of 'domain'; if not specified, check against the default one.
  -x [port:protocol], --tlsa [port:protocol]	Display the TLSA records referring to the couple 'port:protocol' (default ones if not specified); -d and -s (only with required 'label') are required.
  -t <label>, --text-record <label>       	Display the text records having 'label'; -d is required.
Options:
  -e, --insecure                          	Inhibit the DNSSEC validation upon lookup.
  -v, --verbose                           	Display a verbose output of the resolution.
  -n <resolvers>, --servers <resolvers>   	Comma-separated list of resolver servers, overriding the default ones.
  -u <filename>, --trust-anchor <filename>	Specify the file containing the trust anchor.
  -d <domain>, --domain <domain>          	Specify the domain name to use upon resolution process.
  -s <label>, --supplement <label>        	Specify a supplementary 'label' to concatenate/use to query for; the 'label' has to follow the pattern: 'label[:sublabel:proto>|:proto]', e.g. 'http:printer:tcp', or only 'http', or 'http:tcp'.

```

obtaining the same help screen. From there, you can start typing your commands and discovering your services.

## Examples of Use

### Listing Service Types from 'dns-sd.org'

Either using the binary script:

```
[pmaresca@localhost bin]$ ./sd-lookup -d dns-sd.org -l -e
afpovertcp
ftp
http
ipp
pdl-datastream
printer
ssh
```

or using the fat Jar:

```
cd $PROJECT_HOME/build/libs
[pmaresca@localhost libs]$ java -jar iot-discovery-jcli-1.0.jar -d dns-sd.org -l -e
afpovertcp
ftp
http
ipp
pdl-datastream
printer
ssh
```

### Listing Service Instance of type 'ftp'

Either using the binary script:

```
[pmaresca@localhost bin]$ ./sd-lookup -d dns-sd.org -i -s ftp -e
60 "apple quicktime files" ftp.apple.com TCP:21 "txtvers=1" "path=/quicktime"
60 "microsoft developer files" ftp.microsoft.com TCP:21 "txtvers=1" "path=/developer"
59 "restricted, registered users only" pretend-server.dns-sd.org TCP:21 "txtvers=1" "path=/"
```

or using the fat Jar:

```
cd $PROJECT_HOME/build/libs
[pmaresca@localhost libs]$ java -jar iot-discovery-jcli-1.0.jar -d dns-sd.org -i -s ftp -e
60 "apple quicktime files" ftp.apple.com TCP:21 "txtvers=1" "path=/quicktime"
60 "microsoft developer files" ftp.microsoft.com TCP:21 "txtvers=1" "path=/developer"
59 "restricted, registered users only" pretend-server.dns-sd.org TCP:21 "txtvers=1" "path=/"
```

# License
Eclipse Public License - v 1.0
=======
https://www.eclipse.org/legal/epl-v10.html
