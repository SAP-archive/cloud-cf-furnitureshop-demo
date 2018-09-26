namespace com.company.furnitureshop;

entity Wishlist {
  key ProductID : String;
  categoryName : String; 
  productName : String;
  productDesc : String;
  productColor : String;
  productWidth : Integer;
  productHeight : Integer;
  productDepth : Integer;
  productWeight : Integer;
  productPrice : Decimal(10,2);
  productWarranty : Integer;
  materialType : String;
  supplierID : String;
  supplierName : String;
  supplierLocation : String;
  pictureURL : String;
  productRating : Decimal(3,2);
}

entity BackEndProductData
{
	key ProductID : String;
	SUPPLIERID : String;
	SUPPLIERNAME : String;
	PRICE : String;
	STOCK : String;
	DELIVERYDATE : String;
	DISCOUNT : String;
}