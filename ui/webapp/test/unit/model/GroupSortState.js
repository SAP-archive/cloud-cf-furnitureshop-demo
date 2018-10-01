/*global QUnit*/

sap.ui.define([
	"com/company/wishlist/ui/model/GroupSortState",
	"sap/ui/model/json/JSONModel"
], function (GroupSortState, JSONModel) {
	"use strict";

	QUnit.module("GroupSortState - grouping and sorting", {
		beforeEach: function () {
			this.oModel = new JSONModel({});
			// System under test
			this.oGroupSortState = new GroupSortState(this.oModel, function() {});
		}
	});

	QUnit.test("Should always return a sorter when sorting", function (assert) {
		// Act + Assert
		assert.strictEqual(this.oGroupSortState.sort("productPrice").length, 1, "The sorting by productPrice returned a sorter");
		assert.strictEqual(this.oGroupSortState.sort("productName").length, 1, "The sorting by productName returned a sorter");
	});

	QUnit.test("Should return a grouper when grouping", function (assert) {
		// Act + Assert
		assert.strictEqual(this.oGroupSortState.group("productPrice").length, 1, "The group by productPrice returned a sorter");
		assert.strictEqual(this.oGroupSortState.group("None").length, 0, "The sorting by None returned no sorter");
	});


	QUnit.test("Should set the sorting to productPrice if the user groupes by productPrice", function (assert) {
		// Act + Assert
		this.oGroupSortState.group("productPrice");
		assert.strictEqual(this.oModel.getProperty("/sortBy"), "productPrice", "The sorting is the same as the grouping");
	});

	QUnit.test("Should set the grouping to None if the user sorts by productName and there was a grouping before", function (assert) {
		// Arrange
		this.oModel.setProperty("/groupBy", "productPrice");

		this.oGroupSortState.sort("productName");

		// Assert
		assert.strictEqual(this.oModel.getProperty("/groupBy"), "None", "The grouping got reset");
	});
});