package com.company.furnitureshop;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.request.ReadRequest;
import com.sap.cloud.sdk.service.prov.api.response.ErrorResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import com.sap.cloud.sdk.service.prov.api.response.ReadResponse;
import com.sap.cloud.sdk.service.prov.api.operations.Query;
import com.sap.cloud.sdk.service.prov.api.operations.Read;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;

public class BackendService {
	private static final String BACKEND_DESTINATION_NAME = "ONPREM_BACKEND";
	private static final Logger logger = LoggerFactory.getLogger(BackendService.class);

	@Query(serviceName = "CatalogService", entity = "BackEndProductData")
	public QueryResponse getProducts(QueryRequest queryRequest) {
		logger.info("Class:BackendService - now in @Query getProducts()");

		QueryResponse queryResponse = null;
		try {
			logger.info("Class:BackendService - now execute query on Products");
			ODataQueryBuilder qb = ODataQueryBuilder.withEntity("/backend-odata/Product.svc", "OnPremiseProductData")
					.select("ProductID", "SUPPLIERID", "SUPPLIERNAME", "PRICE", "STOCK", "DELIVERYDATE", "DISCOUNT");

			logger.info("Class:BackendService - After ODataQueryBuilder: ");
			ODataQueryResult result = qb.enableMetadataCache().build().execute(BACKEND_DESTINATION_NAME);

			logger.info("Class:BackendService - After calling backend OData V2 service: result: ");

			List<Map<String, Object>> v2BackEndProductsMap = result.asListOfMaps();
			queryResponse = QueryResponse.setSuccess().setData(v2BackEndProductsMap).response();
			return queryResponse;
		} catch (Exception e) {
			logger.error("Class:BackendService - ==> Exception calling backend OData V2 service for Query of Products: "
					+ e.getMessage());

			ErrorResponse errorResponse = ErrorResponse.getBuilder()
					.setMessage("Class:BackendService - There is an error.  Check the logs for the details.")
					.setStatusCode(500).setCause(e).response();
			queryResponse = QueryResponse.setError(errorResponse);
		}
		return queryResponse;
	}

	@Read(entity = "BackEndProductData", serviceName = "CatalogService")
	public ReadResponse getProduct(ReadRequest readRequest) {
		logger.info("Class:BackendService - at Read " + readRequest.getKeys().get("ProductID").toString());
		ReadResponse readResponse = null;

		try {
			logger.info("Class:BackendService - getProduct inside with ProductID = "
					+ readRequest.getKeys().get("ProductID").toString());
			ODataQueryResult readResult = ODataQueryBuilder
					.withEntity("/backend-odata/Product.svc",
							"OnPremiseProductData('" + readRequest.getKeys().get("ProductID").toString() + "')")
					.select("ProductID", "SUPPLIERID", "SUPPLIERNAME", "PRICE", "STOCK", "DELIVERYDATE", "DISCOUNT")
					.enableMetadataCache().build().execute(BACKEND_DESTINATION_NAME);

			BackEndProductEntity readProdEntity = readResult.as(BackEndProductEntity.class);
			readResponse = ReadResponse.setSuccess().setData(readProdEntity).response();

			logger.info("Class:BackendService - After calling backend OData V2 READ service: readResponse : "
					+ readResponse);

		} catch (Exception e) {
			logger.error("==> Exception calling backend OData V2 service for READ of Products: " + e.getMessage());

			ErrorResponse errorResponse = ErrorResponse.getBuilder()
					.setMessage("There is an error.  Check the logs for the details.").setStatusCode(500).setCause(e)
					.response();
			readResponse = ReadResponse.setError(errorResponse);
		}
		return readResponse;
	}

}