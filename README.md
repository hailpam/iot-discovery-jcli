# IoT Discovery Services CLI Application

## Intro

### Verisign Device Services
http://www.verisigninc.com/en_US/security-services/the-internet-of-things/index.xhtml

### Purpose
This project aims at providing a comprehensive command line tool to lookup Device Services. Developers, Customers, etc., may use and contribute to this tool.

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

ERROR: invalid command arguments: Missing command
Usage: java -jar iot-discovery-jcli-1.0.jar [<command>[<arg>]] [options]
Commands:
  -h, --help                              	Display this usage and quit.
  -i, --list-instances                    	Detailed display of service instances; -s and -d are required.
  -l, --list-services                     	Display the service types; -d is required.
  -c [domain], --dnssec-status [domain]   	Check the DNSSEC status of 'domain'; if not specified, check against the default one.
  -x [port:protocol], --tlsa [port:protocol]	Display the TLSA records referring to the couple 'port:protocol' (default ones if not specified); -d and -s are required.
  -t <label>, --text-record <label>       	Display the text records having 'label'; -d is required.
Options
  -e, --insecure                          	Inhibit the DNSSEC validation upon lookup.
  -v, --verbose                           	Display a verbose output of the resolution.
  -n <resolvers>, --servers <resolvers>   	Comma-separated list of resolver servers, overriding the default ones.
  -u <filename>, --trust-anchor <filename>	Specify the file containing the trust anchor.
  -d <domain>, --domain <domain>          	Specify the domain name to use upon resolution process.
  -s <label>, --supplement <label>        	Specify a supplementary 'label' to concatenate/use to query for.

```

## Example of Use
```
cd $PROJECT_HOME
java -jar ./build/libs/iot-discovery-jcli-1.0.jar -d mcn366rzmd2a.1.iotverisign.com -i -s mqtt

mqtt austriamqtt.example.com:1882 60 "qos=1"
mqtt zambiamqtt.example.com:1882 60 "qos=1"
mqtt indiamqtt.example.com:1884 60 "qos=2"
mqtt norwaymqtt.example.com:1884 60 "qos=3"
mqtt ukmqtt.example.com:1884 60 "qos=1"
mqtt germanmqtt.example.com:1882 60 "qos=2"
```

# License
Copyright (c) 2015, Verisign, Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Verisign, Inc. nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL VERISIGN, INC. BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

