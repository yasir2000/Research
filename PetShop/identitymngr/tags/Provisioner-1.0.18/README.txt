
Package contents
================

       README.txt           -- This file
       LICENSE.txt          -- The GNU General Public License (GPL)
       KEYS.txt             -- Release manager's public key for verifying package's integrity
       Provisioner.sh       -- Korn shell script that controls Provisioner's process execution
       Provisioner.cfg      -- Launch configuration for Linux
       bin/                 -- Provisioner binaries 
          Provisioner.jar   -- Executable jar
       conf/                -- XML and dot properties configuration files 
          *.xml
          *.properties
       src/                 -- Source code files (for development)
       lib/                 -- Third-party library files
          *.jar
          


Running Provisioner 
===================

The following instructions show how to run Provisioner:

1) Unpack the archive where you would like to store the binaries, e.g.:
  
   		Linux/Unix: tar zxvf Provisioner-1.0.17.tar.gz
	
	or
	
  		Windows: unzip Provisioner-1.0.17.zip

2) Make sure JAVA_HOME is set to the location of your JDK (1.5+) and that the JAVA_HOME/bin is in your path

3) Run:

		Linux/Unix: ~/Provisioner/bin/Provisioner.sh start
		
	or
	
		Windows: java -jar "C:\..\Provisioner\bin\Provisioner.jar" 20 2 ..\Provisioner\conf\ en US   

For more information, please see http://identitymngr.sourceforge.net/howto-run.html
