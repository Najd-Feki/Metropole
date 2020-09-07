import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';

import { GuestsController } from '../../../ducks/guests/guests.controller';
import { types } from '../../../ducks/guests/guests.types';


@Component({
    selector: 'app-detail-guests',
    templateUrl: './guests.detail.component.html',
    encapsulation: ViewEncapsulation.None
})
export class GuestsDetailComponent implements OnInit, OnDestroy {
    public id: number; //guest id from route
    public sub: any;
    public guest$: any;
    public name: String  // guest name from route 
    

    /**
     * [constructor description]
     * @method  constructor
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-31
     * @param   {ActivatedRoute} private route [description]
     * @param   {GuestsController} private _guests [description]
     * @param   {Store<any>} private _store [description]
     * @return  {[type]} [description]
     */
    constructor(private route: ActivatedRoute, private _guests: GuestsController, private _store: Store<any>) {
        _store.select('guests').subscribe((response) => {
            this.guest$ = response;
        });
    }

    /**
     * [ngOnInit description]
     * @method  ngOnInit
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-31
     * @return  {[type]} [description]
     */
    public ngOnInit() {
        this.route.data.subscribe(data=>{
            if(data.kind=="byid"){
                this.sub = this.route.params.subscribe(params => {
                    this.id = Number(params['id']);
                    this._store.dispatch({
                        type: types.GET_GUESTS,
                        uid: this.id
                    });
                    this._guests.getGuestById(this.id).subscribe((data: any) => {
                        this._store.dispatch({
                            type: types.GET_GUESTS_SUCCESS,
                            payload: data
                        });
                    }, (error: any) => {
                        this._store.dispatch({
                            type: types.GET_GUESTS_FAILURE,
                            error: error.error
                        });
                    });
                    
                });
                
            }
            else if(data.kind=="byname"){
                
                this.sub = this.route.params.subscribe(params => {
                    this.name = String(params['name']);
                    
                console.log(this.name);
                    this._store.dispatch({
                        type: types.GET_GUESTS,
                        uid: this.id
                    });
                    
                    this._guests.getGuestByFullName(this.name).subscribe((data: any) => {
                        this._store.dispatch({
                            type: types.GET_GUESTS_SUCCESS,
                            payload: data
                        });
                    }, (error: any) => {
                        this._store.dispatch({
                            type: types.GET_GUESTS_FAILURE,
                            error: error.error
                        });
                    });
                    
                });
            }
        })
        
    }

    /**
     * [ngOnDestroy description]
     * @method  ngOnDestroy
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-31
     * @return  {[type]} [description]
     */
    public ngOnDestroy() {
        this.sub.unsubscribe();
    }
}
