#!/bin/sh

# Delete all Java class files, and recompile everything
echo "Recompiling Java classes..."
rm *.class
javac *.java

# Create a temporary directory structure for the web application
echo "Creating WAR file..."
mkdir temp
mkdir temp/WEB-INF
mkdir temp/WEB-INF/tlds
mkdir temp/WEB-INF/classes
mkdir temp/WEB-INF/classes/com
mkdir temp/WEB-INF/classes/com/davidflanagan
mkdir temp/WEB-INF/classes/com/davidflanagan/examples
mkdir temp/WEB-INF/classes/com/davidflanagan/examples/servlet

# Now copy our files into those directories
cp *.jsp temp                          # JSP files go at the top level
cp WEB-INF/web.xml temp/WEB-INF        # Configuration files in WEB-INF/
cp WEB-INF/tlds/decor_0_1.tld temp/WEB-INF/tlds
# Java class files go under WEB-INF/classes
cp *.class temp/WEB-INF/classes/com/davidflanagan/examples/servlet

# At this point, the temporary directory contains all our files, so
# we can jar them up into a WAR file with the name javaexamples2.war
# This WAR file can now simply be dropped into the Tomcat webapps/ directory.
cd temp
jar cMf ../javaexamples2.war *

# Now delete the temporary directory hierarchy
cd ..
rm -rf temp
