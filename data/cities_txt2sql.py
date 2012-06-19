def cities(fname):
	f=open(fname, 'r')
	with f:
		for l in f:
			d = l.split('\t')
			yield int(d[0]), d[2], float(d[4]), float(d[5]), d[8], int(d[14])

for id, name, lat, lng, country, population in cities('cities15000.txt'):
	print "INSERT INTO city (id, name, country, population, geometry) VALUES (%d, '%s', '%s', %d, sdo_geometry(2001, 8307, sdo_point_type(%f, %f, 0), NULL, NULL));" % (id, name.replace("'", "''"), country, population, lng, lat)
print 'COMMIT;'
