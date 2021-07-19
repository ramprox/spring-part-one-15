import {Product} from "./product";

export class ProductsPage {

  constructor(public content: Product[],
              public totalElements: number,
              public totalPages: number,
              public first: boolean,
              public last: boolean) {
  }
}
