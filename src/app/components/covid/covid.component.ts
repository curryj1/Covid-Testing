import { Component, OnInit } from '@angular/core';
import { Country} from '../../common/country';
import {CovidService} from'../../_services/covid.service';

@Component({
  selector: 'app-covid',
  templateUrl: './covid.component.html',
  styleUrls: ['./covid.component.css']
})
export class CovidComponent implements OnInit {
options:Country[];
selectedValue:string;
information:any[];
reveal=false;

  constructor(private covidService:CovidService) { }

  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){
    this.covidService.listCountries().subscribe(
      data => { 
        this.options = data;
        console.log(data);
      },
      err => {
        console.log("going through");
        this.options = JSON.parse(err.error).message;
      }
    );

  }

  getByCountry(){
    this.covidService.getByCountry(this.selectedValue).subscribe(
      info=>{
        this.information=info;
        console.log(info);

      },
      err => {
        console.log("going through");
        this.information = JSON.parse(err.error).message;
      }
    )
    
  }

  

}
