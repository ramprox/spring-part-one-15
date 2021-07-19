import { Injectable } from '@angular/core';
import {Product} from "./product";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ProductListParam} from "./productListParam";
import {ProductsPage} from "./productsPage";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<Product[]>('/api/v1/product/all').toPromise();
  }

  public findWithFilter(productListParam: ProductListParam) {
    let queryParams = new HttpParams();
    if(productListParam.productNameFilter != null && productListParam.productNameFilter != '') {
      queryParams = queryParams.set('productNameFilter', productListParam.productNameFilter);
    }
    if(productListParam.minCostFilter != null) {
      queryParams = queryParams.set('minCostFilter', productListParam.minCostFilter);
    }
    if(productListParam.maxCostFilter != null) {
      queryParams = queryParams.set('maxCostFilter', productListParam.maxCostFilter);
    }
    if(productListParam.sortBy != null) {
      queryParams = queryParams.set('sortBy', productListParam.sortBy);
    }
    if(productListParam.direction != null) {
      queryParams = queryParams.set('direction', productListParam.direction);
    }
    queryParams = queryParams.set('page', productListParam.page);
    return this.http.get<ProductsPage>('/api/v1/product/', {params : queryParams}).toPromise();
  }

  public findById(id: number) {
    return this.http.get<Product>(`/api/v1/product/${id}`).toPromise();
  }

  public save(product: Product) {
    return this.http.post<Product>('/api/v1/product', product).toPromise();
  }

  public update(product: Product) {
    return this.http.put<void>('/api/v1/product', product).toPromise();
  }

  public delete(id:number) {
    return this.http.delete<void>(`/api/v1/product/${id}`).toPromise();
  }
}
