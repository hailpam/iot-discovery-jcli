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
Usage: java -jar iot-discovery-jcli-1.0.jar [OPTIONS]
Options:
  -h, --help                              	display this help and exit              
  -v, --verbose                           	turn on verbose output                  
  -c, --dnssec-status                     	check DNSSEC status of DNS server [default server or specified by --server]
  -d DOMAIN, --domain DOMAIN              	domain name to query/check DNSSEC status [optional if -c, --dnssec-status is specified, otherwise it is required.]
  -e, --insecure                          	do not perform DNSSEC validation of DOMAIN [optional]
                                          	DNSSEC validation can also be disabled by setting INSECURE=1 in environment
  -i, --list-instances                    	list details of service instances [--service/--domain is required]
  -l, --list-services                     	list details of services [--domain is required]
  -n SERVER, --server SERVER              	DNS server to override default DNS server [optional]
  -r, --service-record                    	get service record [--service is required]
  -s SERVICENAME, --service SERVICENAME   	service name to query                   
  -t TXTLABEL, --text-record TXTLABEL     	get text record                         
  -u, --trust-anchor                      	specify file containing trust anchor keys [optional]
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

