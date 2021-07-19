export class ProductListParam {

  constructor(public productNameFilter: string,
              public minCostFilter: number | null,
              public maxCostFilter: number | null,
              public sortBy: string,
              public direction: string,
              public page: number) {
  }
}
