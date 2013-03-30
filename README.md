Oracle Spatial Demonstration
============================

This sample application demonstrates the use of some [Oracle Spatial](http://docs.oracle.com/cd/E11882_01/appdev.112/e11830/toc.htm) features with Java. It is furthermore a finger exercise for combining the following Java and JavaScript technologies and frameworks:
- [Mybatis](http://code.google.com/p/mybatis/) for SQL mapping
- [GeoTools](http://geotools.org/) for spatial data processing
- [Spring MVC](http://static.springsource.org/spring/docs/current/spring-framework-reference/html/mvc.html) and [Jackson](http://jackson.codehaus.org/) JSON library for RESTful webservices
- [jQuery](http://jquery.com/) for AJAX stuff
- [OpenLayers](http://openlayers.org/) for web-based GIS applications

Setup (Windows)
---------------
1. Install [Oracle XE](http://www.oracle.com/technetwork/products/express-edition/overview/index.html).
   - Choose ``system`` as system user password.
   - Change Oracle XE's HTTP port as described [here](http://daust.blogspot.de/2006/01/xe-changing-default-http-port.html), since Tomcat uses the same default port (8080).
2. Execute the batch file [``data\setup_data.bat``](data/setup_data.bat) to create and fill a demo schema (``spatialdemo``).
   - Make sure that the ``sqlplus`` and ``java`` command will be found in your search path.
3. Build and run the demo application by using Maven:
   1. ``mvn process-resources`` (installs the Oracle JDBC jar file in your local Maven repository)
   2. ``mvn package tomcat:run``

   On later runs the first command can be skipped.
4. Open [http://localhost:8080/oracle-spatial-demo/](http://localhost:8080/oracle-spatial-demo/).
