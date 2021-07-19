import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductService} from "../model/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  public product = new Product(-1, "", 0.0);
  public isError: boolean = false;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if(param.id == 'new') {
        this.product = new Product(-1, "", 0.0);
        return;
      }
      this.productService.findById(param.id)
        .then(res => {
          this.isError = false;
          this.product = res;
        })
        .catch(err => {
          this.isError = true;
          console.error(err);
        })
    })
  }

  public save() {
    let promise: Promise<any>;
    if(this.product.id == -1) {
      promise = this.productService.save(this.product);
    } else {
      promise = this.productService.update(this.product);
    }
    promise.then(() => {
      this.isError = false;
      this.router.navigateByUrl('/product');
    })
      .catch(err => {
        this.isError = true;
        console.error(err);
      })
  }
}
