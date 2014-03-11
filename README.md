Prerequisites: Git, Maven. Eclipse

Clone Client

> git clone git@github.com:duggankimani/WiraClientAPI.git

Maven Create Eclipse Project

> mvn eclipse:eclipse

Import the project into your eclipse

> File>Import

The test package classes may be ignored;
If this is the case go to Build Path configuration and remove the exclusions. (See screenshot)

To test the Rest Interface, 

Go to:
TestWiraClient.java

Run As Java Application
The server will respond with values similar to ones below
DocumentId = 175
JBPM SessionId = 567
JBPM ProcessInstanceId = 1574

By Default, the request creates an Invoice on EC2 as 'Administator', and submits it for approval.

You can view the document which is 'In Progress' using the credentials [Administrator, pass]

The document is pushed automatically to Mariano [mariano, pass]. Check the latest task.

The Test serves as an example of how the integration would work.

mvn install:install-file -Dfile=../libs/jbpmht_rest_models.jar -DgroupId=com.duggan.workflow.server.rest.model -DartifactId=WiraModels -Dversion=0.9-88 -Dpackaging=jar -DlocalRepositoryPath=libs -DcreateChecksum=true
