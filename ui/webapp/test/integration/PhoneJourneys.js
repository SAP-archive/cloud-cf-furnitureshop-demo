/*global QUnit*/

jQuery.sap.require("sap.ui.qunit.qunit-css");
jQuery.sap.require("sap.ui.thirdparty.qunit");
jQuery.sap.require("sap.ui.qunit.qunit-junit");
QUnit.config.autostart = false;

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
		"com/company/wishlist/ui/test/integration/NavigationJourneyPhone",
		"com/company/wishlist/ui/test/integration/NotFoundJourneyPhone",
		"com/company/wishlist/ui/test/integration/BusyJourneyPhone"
	], function () {
		QUnit.start();
	});
});