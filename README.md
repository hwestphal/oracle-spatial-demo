Oracle Spatial Demonstration
============================

TODO

Setup (Windows)
---------------
1. Install [Oracle XE](http://www.oracle.com/technetwork/products/express-edition/overview/index.html).
   - Choose ``system`` as system user password.
   - Change Oracle XE's HTTP port as described [here](http://daust.blogspot.de/2006/01/xe-changing-default-http-port.html), since Tomcat uses the same default port (8080).
   - Make sure that the ``sqlplus`` command is found in your search path.
2. Execute the batch file ``data\setup_data.bat`` to create and fill a demo schema (``spatialdemo``).
3. Build an run the demo application by using Maven:
   1. ``mvn process-resources``
   2. ``mvn package tomcat:run``

   On later runs only the second command is necessary.
4. Open [http://localhost:8080/oracle-spatial-demo/](http://localhost:8080/oracle-spatial-demo/).