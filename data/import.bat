@echo off
cmd /c java -classpath ojdbc5.jar;sdoutl.jar;sdoapi.jar oracle.spatial.util.SampleShapefileToJGeomFeature -h localhost -p 1521 -s xe -u spatialdemo -d spatialdemo -t shapefile -f world-bounds-a -r 8307 -o 0.005
pause
