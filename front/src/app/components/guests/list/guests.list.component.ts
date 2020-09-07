import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Store } from '@ngrx/store';

import { GuestsController } from '../../../ducks/guests/guests.controller';
import { types } from '../../../ducks/guests/guests.types';

@Component({
    selector: 'app-list-guests',
    templateUrl: './guests.list.component.html',
    encapsulation: ViewEncapsulation.None
})
export class GuestsListComponent implements OnInit {
    public guests$: any;  
    public date;
    public current : boolean = true; //button "show all/present" state
    public buttonName : string ;
    /**
     * [constructor description]
     * @method  constructor
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-30
     * @param   {GuestsController} private _guests [description]
     * @param   {Store<any>} private _store [description]
     * @return  {[type]} [description]
     */
    constructor(private _guests: GuestsController, private _store: Store<any>) {
        _store.select('guests').subscribe((response) => {
            this.guests$ = response;
        });
        
    
    }

    /**
     * [ngOnInit description]
     * @method  ngOnInit
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-30
     * @return  {[type]} [description]
     */
    public ngOnInit() {

       if(this.current){
       this.loadPresent();
       this.buttonName="Show all";
       }
       else{       
       this.loadAll();
       this.buttonName="Show current";
       }
       this.current=!this.current;
       
        this.formatDate();
    }
    public formatDate() {
            var d = new Date(),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();
        
            if (month.length < 2) 
                month = '0' + month;
            if (day.length < 2) 
                day = '0' + day;
        
            this.date=[year, month, day].join('-');
        
    }
    public loadPresent(){
        
        this._store.dispatch({
            type: types.LIST_GUESTS
        });
        this._guests.getCurrentGuests().subscribe((data: any) => {
            this._store.dispatch({
                type: types.LIST_GUESTS_SUCCESS,
                payload: data
            });
        }, (error: any) => {
            this._store.dispatch({
                type: types.LIST_GUESTS_FAILURE
            });
        });
    }
    public loadAll(){
        
        this._store.dispatch({
            type: types.LIST_GUESTS
        });

        this._guests.getGuests().subscribe((data: any) => {
            this._store.dispatch({
                type: types.LIST_GUESTS_SUCCESS,
                payload: data
            });
        }, (error: any) => {
            this._store.dispatch({
                type: types.LIST_GUESTS_FAILURE
            });
        });
    }
    
}
