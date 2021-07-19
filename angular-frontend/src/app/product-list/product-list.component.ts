import { Component, OnInit } from '@angular/core';
import {ProductService} from "../model/product.service";
import {Product} from "../model/product";
import {ProductListParam} from "../model/productListParam";
import {ProductsPage} from "../model/productsPage";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  //public products: Product[] = [];
  public productsPage: ProductsPage | null = null;
  public isError: boolean = false;
  public productNameFilter: string = '';
  public minCostFilter: number | null = null;
  public maxCostFilter: number | null = null;
  public sortBy: string = 'id';
  public direction: string = 'asc';
  public pageNumber: number = 1;

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    //this.retrieveProducts();
    this.getWithFilter();
  }

  private retrieveProducts() {
    this.productService.findAll()
      .then(res => {
        this.isError = false;
        //this.products = res;
      })
      .catch(err => {
        this.isError = true;
        console.log(err);
      })
  }

  getWithFilter() {
    let productFilter: ProductListParam = new ProductListParam(this.productNameFilter,
      this.minCostFilter, this.maxCostFilter, this.sortBy, this.direction, this.pageNumber);
    this.productService.findWithFilter(productFilter)
      .then(res => {
        this.productsPage = res;
      })
  }

  delete(id: number) {
    this.productService.delete(id)
      .then(() => {
        //this.retrieveProducts();
        this.pageNumber = 1;
        this.getWithFilter();
      })
  }

  getSortClass(column: string) {
    if(this.sortBy == column) {
      return this.direction == 'asc' ? 'fa fa-sort-down' : 'fa fa-sort-up';
    }
    return 'fa fa-sort-down';
  }

  getPreviousClass() {
    let result: string = 'page-item';
    if(this.productsPage?.first) {
      result += ' disabled';
    }
    return result;
  }

  getNextClass() {
    let result: string = 'page-item';
    if(this.productsPage?.last) {
      result += ' disabled';
    }
    return result;
  }

  getPageNumberClass(pageNumber: number) {
    let result: string = 'page-item';
    if(this.pageNumber == pageNumber) {
      result += ' active';
    }
    return result;
  }

  sort(column: string) {
    this.sortBy = column;
    this.direction = this.direction == 'asc' ? 'desc' : 'asc';
    this.getWithFilter();
  }

  pageClick(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.getWithFilter();
  }

  previousClick() {
    this.pageClick(this.pageNumber - 1);
  }

  nextClick() {
    this.pageClick(this.pageNumber + 1);
  }

  isProductsExist() {
    return this.productsPage != null && this.productsPage.totalElements > 0;
  }

  numbers() {
    if(this.productsPage != null) {
      let nums: number[] = [];
      for(let i: number = 0; i < this.productsPage?.totalPages; i++) {
        nums[i] = i + 1;
      }
      return nums;
    }
    return [1];
  }
}
