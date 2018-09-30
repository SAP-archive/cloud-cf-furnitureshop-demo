/*global QUnit*/

jQuery.sap.require("sap.ui.qunit.qunit-css");
jQuery.sap.require("sap.ui.thirdparty.qunit");
jQuery.sap.require("sap.ui.qunit.qunit-junit");
QUnit.config.autostart = false;

// We cannot provide stable mock data out of the template.
// If you introduce mock data, by adding .json files in your webapp/localService/mockdata folder you have to provide the following minimum data:
// * At least 3 Wishlist in the list

sap.ui.require([
	"sap/ui/test/Opa5",
	"com/company/wishlist/ui/test/integration/pages/Common",
	"sap/ui/test/opaQunit",
	"com/company/wishlist/ui/test/integration/pages/App",
	"com/company/wishlist/ui/test/integration/pages/Browser",
	"com/company/wishlist/ui/test/integration/pages/Master",
	"com/company/wishlist/ui/test/integration/pages/Detail",
	"com/company/wishlist/ui/test/integration/pages/NotFound"
], function (Opa5, Common) {
	"use strict";
	Opa5.extendConfig({
		arrangements: new Common(),
		viewNamespace: "com.company.wishlist.ui.view."
	});

	sap.ui.require([
		"com/company/wishlist/ui/test/integration/MasterJourney",
		"com/company/wishlist/ui/test/integration/NavigationJourney",
		"com/company/wishlist/ui/test/integration/NotFoundJourney",
		"com/company/wishlist/ui/test/integration/BusyJourney"
	], function () {
		QUnit.start();
	});
});