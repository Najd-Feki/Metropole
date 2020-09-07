import { Component, OnInit, ViewEncapsulation, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import _ from 'lodash';

import { GuestsController } from '../../../ducks/guests/guests.controller';
import { types as GuestTypes } from '../../../ducks/guests/guests.types';
import { RoomsController } from '../../../ducks/rooms/rooms.controller';
import { types } from '../../../ducks/rooms/rooms.types';

@Component({
    selector: 'app-form-guests',
    templateUrl: './guests.form.component.html',
    encapsulation: ViewEncapsulation.None
})
export class GuestsFormComponent implements OnInit, OnDestroy {
    public id: number;
    public name : String;
    public sub: any;
    public guest$: any;
    public oldRoomId ;
    public rooms:any;


    public form: any = {
        clientId:0,
        fullName: '',
        phone: 0,
        room :{roomId:0} ,
        cinPass: '',
        dob: '',
        dateIssue: '',
        bills: null,
        startDate: null,
        endDate:null,
        reservation : null,
        nationality : ''
    };

    /**
     * [constructor description]
     * @method  constructor
     * @author jackfiallos
     * @version [version]
     * @date    2017-11-01
     * @param   {[type]} private [description]
     * @param   {[type]} private [description]
     * @param   {[type]} private [description]
     * @param   {[type]} private [description]
     * @param   {[type]} private [description]
     * @return  {[type]} [description]
     */
    constructor(private _rooms:RoomsController, private route: ActivatedRoute, private _router: Router, private _guest: GuestsController, private _store: Store<any>,private _room : RoomsController) {
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
        /////////////////////////////////////rooms/////////////////////////////////
        this._store.dispatch({
            type: types.LIST_ROOMS
        });

        this._rooms.getRooms().subscribe((data: any) => {
            this._store.dispatch({
                type: types.LIST_ROOMS_SUCCESS,
                payload: data
            });
            this.rooms=data;
        }, (error: any) => {
            this._store.dispatch({
                type: types.LIST_ROOMS_FAILURE
            });
        });
        ///////////////////////////////////////////////rooms/////////////////////////////////////
        this.route.data.subscribe(data=>{
            if(data.kind=="new"){
        this.sub = this.route.params.subscribe(params => {
            this.id = Number(params['id']);

            if (!isNaN(this.id)) {
                this._store.dispatch({
                    type: GuestTypes.GET_GUESTS,
                    uid: this.id
                });

                this._guest.getGuestById(this.id).subscribe((data: any) => {
                    this.form = {
                        clientId:data.clientId,
                        fullName:data.fullName,
                        phone: data.phone,
                        room: data.room,
                        cinPass: data.cinPass,
                        dob: data.dob,
                        dateIssue: data.dateIssue,
                        bills: data.bills,
                        startDate: data.startDate,
                        endDate:data.endDate,
                        reservation : data.reservation,
                        nationality : data.nationality
                    };
                    
                    if(this.form.room!=null)
                    this.oldRoomId=this.form.room.roomId;

                    this._store.dispatch({
                        type: GuestTypes.GET_GUESTS_SUCCESS,
                        payload: data
                    });
                }, (error: any) => {
                    this._store.dispatch({
                        type: GuestTypes.GET_GUESTS_FAILURE,
                        error: error.error
                    });
                });
            }
        });
    }
    else if(data.kind=='exist'){
        this.sub = this.route.params.subscribe(params => {
            this.name = String(params['name']);

          //  if (!isNaN(this.id)) {
                this._store.dispatch({
                    type: GuestTypes.GET_GUESTS,
                    uid: this.id
                });

                this._guest.getGuestByFullName(this.name).subscribe((data: any) => {
                    this.form = {
                        clientId:data.clientId,
                        fullName:data.fullName,
                        phone: data.phone,
                        room: data.room,
                        cinPass: data.cinPass,
                        dob: data.dob,
                        dateIssue: data.dateIssue,
                        bills: data.bills,
                        startDate: data.startDate,
                        endDate:data.endDate,
                        reservation : data.reservation,
                        nationality : data.nationality
                    };
                    
                    
                    this.oldRoomId=this.form.room.roomId;

                    this._store.dispatch({
                        type: GuestTypes.GET_GUESTS_SUCCESS,
                        payload: data
                    });
                }, (error: any) => {
                    this._store.dispatch({
                        type: GuestTypes.GET_GUESTS_FAILURE,
                        error: error.error
                    });
                });
          //  }
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
      //  this.sub.unsubscribe();
    }

    /**
     * [onSubmit description]
     * @method  onSubmit
     * @author jackfiallos
     * @version [version]
     * @date    2017-11-09
     * @param   {MouseEvent} e [description]
     * @return  {[type]} [description]
     */
    public onSubmit(e: MouseEvent) {
        
        e.preventDefault();

        if (isNaN(this.id)) {
            // dispatch create
            this._store.dispatch({
                type: GuestTypes.CREATE_GUESTS
            });

            // request create guest
            // making the room occupied if its assigned to a client
            if (this.form.room!=null) 
            if (this.form.room.roomId!=this.oldRoomId)
                this._room.updateRoom(
                this._room.getRoomById(this.form.room.roomId).subscribe(
                room => room.status = 'occupied'))
            // making the room occupied if its assigned to a client
            this._guest.createGuest(this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: GuestTypes.CREATE_GUESTS_SUCCESS,
                    payload: data
                });
                

                this._router.navigate(['/guests/view', data.clientId]);
            }, (error: any) => {
                this._store.dispatch({
                    type: GuestTypes.CREATE_GUESTS_FAILURE,
                    error: error.error
                });
            });
        } else {
            // dispatch update
            this._store.dispatch({
                type: GuestTypes.UPDATE_GUESTS
            });

            // request create guest
             // making the room occupied if its assigned to a client
            if (this.form.room!=null) 
            if (this.form.room.roomId!=this.oldRoomId)
            this._room.updateRoom(
                this._room.getRoomById(this.form.room.roomId).subscribe(
                room => room.status = 'occupied'))
            // making the room occupied if its assigned to a client
            
            
            this._guest.updateGuest(this.id, this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: GuestTypes.UPDATE_GUESTS_SUCCESS,
                    payload: data
                });
                

                this._router.navigate(['/guests/view', data.clientId]);
            }, (error: any) => {
                this._store.dispatch({
                    type: GuestTypes.UPDATE_GUESTS_FAILURE,
                    error: error.error
                });
            });
        }
    }
}
