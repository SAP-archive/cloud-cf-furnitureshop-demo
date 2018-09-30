using com.company.furnitureshop from '../db/data-model';

service CatalogService {
  entity Wishlist @read @update as projection on furnitureshop.Wishlist;
}