package com.company.furnitureshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.cloud.sdk.service.prov.api.operations.Update;
import com.sap.cloud.sdk.service.prov.api.response.UpdateResponse;
import com.sap.cloud.sdk.service.prov.api.request.UpdateRequest;
import com.sap.cloud.sdk.service.prov.api.EntityData;
import com.sap.cloud.sdk.service.prov.api.ExtensionHelper;
import com.sap.cloud.sdk.service.prov.api.DataSourceHandler;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.sap.cloud.sdk.service.prov.api.response.ErrorResponse;

public class WishlistHandler {

	private static final Logger logger = LoggerFactory.getLogger(WishlistHandler.class.getName());

	@Update(entity = "Wishlist", serviceName = "CatalogService")
	public UpdateResponse updateWishlist(UpdateRequest updateRequest, ExtensionHelper extensionHelper) {

		EntityData entityData = updateRequest.getData();

		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put("ProductID", entityData.getElementValue("ProductID"));
		DataSourceHandler handler = extensionHelper.getHandler();

		try {
			EntityData existingWishlistData = handler.executeRead("Wishlist", keyMap, getWishlistPropertyNames());

			EntityData updatedWishlistData = EntityData.getBuilder(existingWishlistData).removeElement("productRating")
					.addElement("productRating", entityData.getElementValue("productRating"))
					.buildEntityData("Wishlist");

			handler.executeUpdate(updatedWishlistData, updateRequest.getKeys(), false);

		} catch (Exception e) {

			ErrorResponse err = ErrorResponse.getBuilder()
					.setMessage("Faild to Update Wishlist. Check log for more details.").setStatusCode(500).response();
			return UpdateResponse.setError(err);
		}
		return UpdateResponse.setSuccess().response();

	}

	public static List<String> getWishlistPropertyNames() {
		List<String> propertyNames = new ArrayList<String>();

		propertyNames.add("ProductID");
		propertyNames.add("categoryName");
		propertyNames.add("productName");
		propertyNames.add("productDesc");
		propertyNames.add("productColor");
		propertyNames.add("productWidth");
		propertyNames.add("productHeight");
		propertyNames.add("productDepth");
		propertyNames.add("productWeight");
		propertyNames.add("productPrice");
		propertyNames.add("productWarranty");
		propertyNames.add("materialType");
		propertyNames.add("supplierID");
		propertyNames.add("supplierName");
		propertyNames.add("supplierLocation");
		propertyNames.add("pictureURL");
		propertyNames.add("productRating");

		return propertyNames;
	}

}