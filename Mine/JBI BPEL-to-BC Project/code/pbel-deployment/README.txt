To build projects you have to:
1. Install maven
2. Unpack this package and go to its root
3. Start console in this root directory
4. Rename install-artifacts.bat.txt to install-artifacts.bat and run
install-artifacts.bat (I could not send install-artifacts.bat because
GMail does not allow sending executables files for security reasons)
5. Run maven clean package
6. At the directory bpel-deployment-jbi-se\target you will see the files:
bpel-deployer-1.0.0-cmd.zip - command line application of
bpel-deployment package
bpel-deployer-1.0.0-jbi-se.zip - JBI SE package

So to run command line application of bpel-deployment you should
unpack the  bpel-deployer-1.0.0-cmd.zip  and you will see run.cmd
file. This file runs command line application.
You can notice that the bpel-deployer-1.0.0-cmd.zip and
bpel-deployer-1.0.0-jbi-se.zip are almost the same, so bpel-deployer
can run as JBI SE and as command line application

run.cmd USAGE:

run help - prints HELP
run source\hello-jbi.zip sun-bpel-deployment  - creates JBI deployment
package for source\hello-jbi.zip

Note! You can find 2 source test packages in test\source.