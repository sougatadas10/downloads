#!/bin/sh
PATH=$PATH:/home/soadevopsuser
export PATH
#whoami
[ -e /var/jenkins_home/publishPar.properties ] && rm /var/jenkins_home/publishPar.properties
cp $propertyFileName /var/jenkins_home/publishPar.properties
echo '#' >> /var/jenkins_home/publishPar.properties
FileNamePath=/var/jenkins_home/jobs/CastIronDeployment_dev/workspace/$Filename
echo FileNamePath=$FileNamePath >> /var/jenkins_home/publishPar.properties
echo enableStart=$enableStart >> /var/jenkins_home/publishPar.properties
#cd /var/jenkins_home
echo $targetenv
if [ $targetenv = Dev ]; then
	/var/jenkins_home/bin/testrunner.sh -arsPublishandStart /var/jenkins_home/jobs/CastIronDeployment_dev/workspace/CastIron-Deployment-soapui-project.xml
fi
