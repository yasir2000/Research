mvn install:install-file -Dfile=bpel-deployment-jbi-se\lib\jbi-api-1.0.jar -DgroupId=javax.jbi -DartifactId=jbi-api -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=bpel-deployment-jbi-se\lib\saxon-9.1.0.8.jar -DgroupId=net.sf.saxon -DartifactId=saxon -Dversion=9.1.0.8 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=bpel-deployment-jbi-se\lib\activation-1.1.jar -DgroupId=javax.activation -DartifactId=activation -Dversion=1.1 -Dpackaging=jar -DgeneratePom=true