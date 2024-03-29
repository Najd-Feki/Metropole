import { Component, OnInit, ViewEncapsulation, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import _ from 'lodash';

import { RoomsController } from '../../../ducks/rooms/rooms.controller';
import { types as RoomTypes } from '../../../ducks/rooms/rooms.types';

@Component({
    selector: 'app-form-rooms',
    templateUrl: './rooms.form.component.html',
    encapsulation: ViewEncapsulation.None
})
export class RoomsFormComponent implements OnInit, OnDestroy {
    public id: number;
    public sub: any;
    public room$: any;

    public types: any[] = [{
        id: 'single',
        name: 'Single'
    }, {
        id: 'double',
        name: 'Double'
    }, {
        id: 'triple',
        name: 'Triple'
    }];

    public form: any = {
        roomId: null,
        rate: 0,
        roomType: null,
        status: null
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
    constructor(private route: ActivatedRoute, private _router: Router, private _room: RoomsController, private _store: Store<any>) {
        _store.select('rooms').subscribe((response) => {
            this.room$ = response;
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
        this.sub = this.route.params.subscribe(params => {
            this.id = Number(params['id']);

            if (!isNaN(this.id)) {
                this._store.dispatch({
                    type: RoomTypes.GET_ROOMS,
                    uid: this.id
                });

                this._room.getRoomById(this.id).subscribe((data: any) => {
                    this.form = {
                        roomId: data.roomId,
                        rate: data.rate,
                        roomType: data.roomType,
                        status: data.status
                    };

                    this._store.dispatch({
                        type: RoomTypes.GET_ROOMS_SUCCESS,
                        payload: data
                    });
                }, (error: any) => {
                    this._store.dispatch({
                        type: RoomTypes.GET_ROOMS_FAILURE,
                        error: error.error
                    });
                });
            }
        });
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

    /**
     * [e description]
     * @type {[type]}
     */
    public onSubmit(e: MouseEvent) {
        e.preventDefault();

        if (isNaN(this.id)) {
            // dispatch create
            this._store.dispatch({
                type: RoomTypes.CREATE_ROOMS
            });

            // request create room
            this._room.createRoom(this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: RoomTypes.CREATE_ROOMS_SUCCESS,
                    payload: data
                });
                console.log(this.form);
                

                this._router.navigate(['/rooms/view', data.roomId]);
            }, (error: any) => {
                this._store.dispatch({
                    type: RoomTypes.CREATE_ROOMS_FAILURE,
                    error: error.error
                });
            });
        } else {
            // dispatch update
            this._store.dispatch({
                type: RoomTypes.UPDATE_ROOMS
            });

            // request create room
            console.log(this.form);
            
            this._room.updateRoom(this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: RoomTypes.UPDATE_ROOMS_SUCCESS,
                    payload: data
                });

                this._router.navigate(['/rooms/view', data.roomId]);
            }, (error: any) => {
                this._store.dispatch({
                    type: RoomTypes.UPDATE_ROOMS_FAILURE,
                    error: error.error
                });
            });
        }
    }
}
