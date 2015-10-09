#!/bin/bash

PATH_TO_JAVA="workspaces/java/iot-github/iot-discovery-jcli"

# Tools
JCLI="java -jar $DEV_HOME/$PATH_TO_JAVA/build/libs/iot-discovery-jcli-1.0.jar"

# Comparisons to be done
OPTIONS=(
# List Text Records
'-d com -t example -n alabalala'
'-d com -t example' 
'-d coma -t example'
'-d coma -t example'
'-d com -t example -e'
'-d com -t example -n 8.8.4.4'
'-d come -t example -n 1.2.3.4'
'-d com -t exampleXYZ -n 8.8.8'
'-d com -t exampleXYZ'
'-d mail.com -t www'
'-d mail.com -t www -e'
'-d mail.com -t www -n 8.8.8.8'
'-d mail.com -t www -n 208.67.222.222'
'-d 47zlpxulsrha.1.iotverisign.com -t indiamqtt._mqtt._tcp'
'-d 47zlpxulsrha.1.iotverisign.com -t indiamqtt._mqtt._tcp -e'
'-d 47zlpxulsrha.1.iotverisign.com -t indiamqtt._mqtt._tcp -n 8.8.8.8'
'-d 47zlpxulsrha.1.iotverisign.com -t ukamqtt.mqtt.tcp'
'-d 47zlpxulsrha.1.iotverisign.com -t ukmqtt._mqtt._tcp -n 1.2.3.4'
'-d 47zlpxulsrha.1.iotverisign.com -t ukmqtt.mqtt.tcp -e'
'-d 47zlpxulsrha.1.iotverisign.com -t ukmqtt.mqtt.tcp -n 8.8.8.8'
'-d 47zlpxulsrha.1.iotverisign.com -t ukmqtt._mqtt._tcp -n 208.67.222.222'
# List Instances
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -i'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -i -e'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -i -n 8.8.8.8'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -i -n 1.2.3.4'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqtt -i -n 1.2.3.4'
'-d mcn366rzmd2a.1.iotverisign.com -s mqttXYZ -i -e'
'-d mcn366rzmd2a.1.iotverisign.com -s abracadabra -i -e'
'-d mcn366rzmd2a.1.iotverisign.com -s abracadabra -i -n 8.8.4.4'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqtt -i -e'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqttXYZ -i -n 8.8.8.8'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqttXYZ -i -n 208.67.222.222'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -i -n 208.67.222.222'
'-d dns-sd.org -s pdl-datastream -i -e'
'-d dns-sd.org -s pdl-datastream -i -e -n 8.8.8.8'
'-d dns-sd.org -s ftp -i -e'
'-d dns-sd.org -s ftp -i -e -n 8.8.8.8'
'-d dns-sd.org -s afpovertcp -i -e'
'-d dns-sd.org -s afpovertcp -i -n 8.8.8.8'
'-d avu7unxcs7ia.1.iotverisign.com -s coap:udp -i -e'
'-d avu7unxcs7ia.1.iotverisign.com -s coapspecial:udp -i -e'
'-d dns-sd.org -s http:printer:tcp -i -e'
'-d dns-sd.org -s http:printer:tcp -i -n 8.8.8.8'
# DNSSEC Check
'-c'
'-c -n 8.8.8.8'
'-c -n 208.67.222.222'
'-cgoogle.com'
'-cgoogle.com -n 208.67.222.222'
'-cexample.com'
'-cexample.com -n 8.8.8.8'
'-cexample.com -n 208.67.222.222'
'-cwww.mail.com'
'-cwww.mail.com -n 8.8.8.8'
'-cmail.com'
'-cmail.com -n 8.8.8.8'
'-cexample.coma'
'-cexample.coma -n 8.8.4.4'
# List Types
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -l'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -l -e'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -l -n 8.8.8.8'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqtt -l'
'-d mcn366rzmd2a.1.iotverisign.comXYZ -s mqttXYZ -l -e'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -l -n 1.2.3.4'
'-d mcn366rzmd2a.1.iotverisign.com -s mqttXYZ -l -e'
'-d mcn366rzmd2a.1.iotverisign.com -s mqttXYZ -l -n 8.8.4.4'
'-d mcn366rzmd2a.1.iotverisign.com -s mqtt -l -n 208.67.222.222'
'-d mcn366rzmd2a.1.iotverisign.com -s mqttXYZ -l -n 208.67.222.222'
'-d dns-sd.org -l -e'
'-d dns-sd.org -l -n 8.8.8.8'
# TLSA
'-d djk6epmd4tlq.1.iotverisign.com -s Device2 -x'
'-d djk6epmd4tlq.1.iotverisign.comXYZ -s Device2 -x'
'-d djk6epmd4tlq.1.iotverisign.com -s DeviceXYZ -x'
'-d djk6epmd4tlq.1.iotverisign.com -s Device2 -x -e'
'-d mcn366rzmd2a.1.iotverisign.com -s Device2 -x443:tcp -e'
'-d djk6epmd4tlq.1.iotverisign.com -s Device2 -x -n 8.8.8.8'
'-d djk6epmd4tlq.1.iotverisign.com -s DeviceXYZ -x -n 1.2.3.4'
'-d djk6epmd4tlq.1.iotverisign.com -s DeviceXYZ -x -n 8.8.8.8'
'-d djk6epmd4tlq.1.iotverisign.com -s DeviceXYZ -x -n 208.67.222.222'
'-d djk6epmd4tlq.1.iotverisign.com -s Device2 -x -n 208.67.222.222'
)


# Comparison loop
CNTR=1
for OPT in "${OPTIONS[@]}"
do
    echo "->> Testing '$OPT'<<-"
    echo "-++  Test $CNTR  of ${#OPTIONS[*]} ++-"
    # TTL is stripped out, cause it may be variable
    $JCLI $OPT
    echo "-** Exit Code $? **-"
    let CNTR=CNTR+1
done
