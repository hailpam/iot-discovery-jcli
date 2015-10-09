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
cd $PROJECT_HOME
gradle clean fatJar
```

## Usage
```
cd $PROJECT_HOME
java -jar build/libs/iot-discovery-jcli-1.0.jar

[pmaresca@localhost tools]$ jcli -h
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

## Example of Use
```
cd $PROJECT_HOME
java -jar /build/libs/iot-discovery-jcli-1.0.jar -d mcn366rzmd2a.1.iotverisign.com -i -s mqtt

mqtt austriamqtt.example.com:1882 60 "qos=1"
mqtt zambiamqtt.example.com:1882 60 "qos=1"
mqtt indiamqtt.example.com:1884 60 "qos=2"
mqtt norwaymqtt.example.com:1884 60 "qos=3"
mqtt ukmqtt.example.com:1884 60 "qos=1"
mqtt germanmqtt.example.com:1882 60 "qos=2"
```

# License
Eclipse Public License - v 1.0
https://www.eclipse.org/legal/epl-v10.html
