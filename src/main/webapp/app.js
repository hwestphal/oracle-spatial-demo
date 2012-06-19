$(function() {
	"use strict";

	var Click = OpenLayers.Class(OpenLayers.Control, {
		defaultHandlerOptions : {
			'single' : true,
			'double' : false,
			'pixelTolerance' : 0,
			'stopSingle' : false,
			'stopDouble' : false
		},

		initialize : function(options) {
			this.handlerOptions = OpenLayers.Util.extend({},
					this.defaultHandlerOptions);
			OpenLayers.Control.prototype.initialize.apply(this, arguments);
			this.handler = new OpenLayers.Handler.Click(this, {
				'click' : this.trigger
			}, this.handlerOptions);
		},

		trigger : function(e) {
			var lonlat = map.getLonLatFromViewPortPx(e.xy);
			$.getJSON('rest/country', {
				'lat' : lonlat.lat,
				'lng' : lonlat.lon
			}, function(data) {
				countryLayer.removeAllFeatures();
				var polygons = data.geometry;
				if (!polygons[0].length) {
					polygons = [ polygons ];
				}
				var polygonList = [];
				polygons.forEach(function(polygon) {
					var linearRing = new OpenLayers.Geometry.LinearRing();
					polygon.forEach(function(item) {
						var point = new OpenLayers.Geometry.Point(item.lng,
								item.lat);
						linearRing.addComponent(point, null);
					});
					polygonList.push(new OpenLayers.Geometry.Polygon(
							[ linearRing ]));
				});
				var feature = new OpenLayers.Feature.Vector(
						new OpenLayers.Geometry.MultiPolygon(polygonList),
						null, {
							'label' : data.name,
							'fillColor' : '#2299ee',
							'fillOpacity' : 0.4
						});
				countryLayer.addFeatures([ feature ]);
			});
		}
	});

	var onMapChanged = function(e) {
		var extent = map.getExtent().toArray();
		var zoom = map.getZoom();
		$.getJSON('rest/cities', {
			'east' : Math.min(Math.max(extent[2], -180), 180),
			'west' : Math.min(Math.max(extent[0], -180), 180),
			'north' : Math.min(Math.max(extent[3], -90), 90),
			'south' : Math.min(Math.max(extent[1], -90), 90),
			'population' : Math.floor(5000000 / (1 << Math.max(0, zoom - 3)))
		}, function(data) {
			cityLayer.removeAllFeatures();
			data.forEach(function(item) {
				var point = new OpenLayers.Geometry.Point(item.point.lng,
						item.point.lat);
				var feature = new OpenLayers.Feature.Vector(point, null, {
					'label' : item.name,
					'fillColor' : '#ee9900',
					'fillOpacity' : 0.4,
					'strokeColor' : '#ee9900',
					'pointRadius' : Math.max(2, Math.sqrt(item.population)
							/ 150 * Math.max(1, zoom - 4))
				});
				cityLayer.addFeatures([ feature ]);
			});
		});
	};

	var map = new OpenLayers.Map('map', {
		eventListeners : {
			'moveend' : onMapChanged,
		}
	});

	var wmsLayer = new OpenLayers.Layer.WMS('OpenLayers WMS',
			'http://vmap0.tiles.osgeo.org/wms/vmap0?', {
				layers : 'basic'
			});

	var countryLayer = new OpenLayers.Layer.Vector('Countries');
	var cityLayer = new OpenLayers.Layer.Vector('Cities');

	map.addLayers([ wmsLayer, countryLayer, cityLayer ]);
	map.addControl(new OpenLayers.Control.PanZoomBar());
	map.addControl(new OpenLayers.Control.LayerSwitcher());
	map.addControl(new OpenLayers.Control.MousePosition());

	map.setCenter(new OpenLayers.LonLat(0, 0), 3);

	var click = new Click();
	map.addControl(click);
	click.activate();
});
