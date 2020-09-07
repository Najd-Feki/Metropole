import { Component, OnInit, ViewEncapsulation, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import _ from 'lodash';
import { MatDatepickerModule } from '@angular/material/datepicker';

import { startWith, map } from 'rxjs/operators';

import { BookingsController } from '../../../ducks/bookings/bookings.controller';
import { RoomsController } from '../../../ducks/rooms/rooms.controller';
import { GuestsController } from '../../../ducks/guests/guests.controller';
import { types as BookingTypes } from '../../../ducks/bookings/bookings.types';
import { types as RoomTypes } from '../../../ducks/rooms/rooms.types';
import { types as GuestTypes } from '../../../ducks/guests/guests.types';
import { AuthController } from '../../../ducks/auth/auth.controller';

@Component({
    selector: 'app-form-bookings',
    templateUrl: './bookings.form.component.html',
    encapsulation: ViewEncapsulation.None
})
export class BookingsFormComponent implements OnInit, OnDestroy {
    public id: number;
    public sub: any;
    public booking$: any;
    public rooms$: any;
    public guests$: any;
    public employee$ : any;

    public guestControl: FormControl = new FormControl();
    public guestOptions: any[] = [];
    public guestFiltered: Observable<any[]>;

    public availableRooms: any[];
    public selectedRooms: any [];
    public dropdownSettings: any = {
        singleSelection: false,
        text: 'Select Rooms',
        selectAllText: 'Select All',
        unSelectAllText: 'UnSelect All',
        enableSearchFilter: true,
        classes: 'multiselect'
    };

    public form: any = {
        startDate: new Date(),
        endDate: new Date(),
        nombrePR: 1,
        children: 0,
        type: 'desk',
        confirmed: false,
        client: null,
        rooms: null,
        comment: null,
        employee:null,
        fullName:null
    };

    public types: string[] = ['online', 'phone', 'agency', 'desk'];
    employee:any;
    

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
    constructor(
        private route: ActivatedRoute,
        private _router: Router,
        private _bookings: BookingsController,
        private _room: RoomsController,
        private _guest: GuestsController,
        private _store: Store<any>,
        private dateAdapter: DateAdapter<any>,
        private _authentificationController : AuthController
    ) {
        this.dateAdapter.setLocale('en-US');

        _store.select('bookings').subscribe((response) => {
            this.booking$ = response;
        });

        _store.select('rooms').subscribe((response) => {
            this.rooms$ = response;
        });

        _store.select('guests').subscribe((response) => {
            this.guests$ = response;
        });

        // request room list
        this._store.dispatch({
            type: RoomTypes.LIST_ROOMS
        });

        this._room.getRooms('availables').subscribe((data: any) => {
            this.availableRooms = 
            _.map(data, (item) => {
                return {
                    id: item.roomId,
                    itemName: `${item.roomId} (${item.roomType})`
                };
            });
            this._store.dispatch({
                type: RoomTypes.LIST_ROOMS_SUCCESS,
                payload: data
            });
        }, (error: any) => {
            this._store.dispatch({
                type: RoomTypes.LIST_ROOMS_FAILURE,
                error: error.error
            });
        });

        this._store.dispatch({
            type: GuestTypes.LIST_GUESTS
        });

        // request guest list
        this._guest.getGuests().subscribe((data: any) => {
            this.guestOptions = _.map(data, (guest) => {
                const email = (guest.phone !== null) ? `(${guest.phone})` : '';
                return Object.assign({}, {
                    id: guest.clientId,
                    name: `${guest.fullName} ${guest.phone}`
                });
            });

            this.guestFiltered = this.guestControl.valueChanges
                .pipe(
                    startWith(null),
                    map((guest) => {
                        return (guest && typeof guest === 'object') ? guest.fullName : guest;
                    }),
                    map((fullName) => {
                        return fullName ? this.filter(fullName) : this.guestOptions.slice();
                    })
                );

            this._store.dispatch({
                type: GuestTypes.LIST_GUESTS_SUCCESS,
                payload: data
            });
        }, (error: any) => {
            this._store.dispatch({
                type: GuestTypes.LIST_GUESTS_FAILURE,
                error: error.error
            });
        });
    }

    /**
     * [sub description]
     * @type {[type]}
     */
    public ngOnInit() {
//getting the current employee
        this._authentificationController.getUser().subscribe(data=>this.employee=data);
 ////////////
        this.sub = this.route.params.subscribe(params => {
            this.id = Number(params['id']);

            if (!isNaN(this.id)) {
                this._store.dispatch({
                    type: BookingTypes.GET_BOOKINGS,
                    uid: this.id
                });
                

                this._bookings.getBookingById(this.id).subscribe((data: any) => {
                    this.form = {
                        fullName:data.fullName,
                        checkin: data.startDate,
                        checkout: data.endDate,
                        nombrePR: data.nombrePR,
                        children: data.children,
                        type: data.type,
                        confirmed: data.confirmed,
                        comment: data.comment,
                        employee:data.employee,
                        client:data.client
                    };

                    this.selectedRooms = data.room
                    // _.map(data.room, (room) => {
                    //     return {
                    //         id: room.uid,
                    //         itemName: `${room.name} (${room.type})`
                    //     }; 
                    // });

                    this.guestControl.setValue({
                        id: data.clientid,
                        name: `${data.client.fullName}`
                    });

                    this._store.dispatch({
                        type: BookingTypes.GET_BOOKINGS_SUCCESS,
                        payload: data
                    });
                }, (error: any) => {
                    this._store.dispatch({
                        type: BookingTypes.GET_BOOKINGS_FAILURE,
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

        // get guest id value
        this.form.client = this.guestControl.value ? this.guestControl.value : null;

        // get selected rooms
       // this.form.rooms = _.map(this.selectedRooms, (room) => room.id);
        this.form.rooms=this.selectedRooms
        console.log(this.selectedRooms);
        

        if (isNaN(this.id)) {
            // dispatch create
            this._store.dispatch({
                type: BookingTypes.CREATE_BOOKINGS
            });

            // request create booking
            this.form.employee=this.employee;
            console.log("lenna");
            
            console.log(this.form);
            
            this._bookings.createBooking(this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: BookingTypes.CREATE_BOOKINGS_SUCCESS,
                    payload: data
                });

                this._router.navigate(['/bookings/view', data.id]);
            }, (error: any) => {
                this._store.dispatch({
                    type: BookingTypes.CREATE_BOOKINGS_FAILURE,
                    error: error.error
                });
            });
        } else {
            // dispatch update
            this._store.dispatch({
                type: BookingTypes.UPDATE_BOOKINGS
            });

            // request update booking
            this.form.employee=this.employee; //edit new
            this._bookings.updateBooking(this.id, this.form).subscribe((data: any) => {
                this._store.dispatch({
                    type: BookingTypes.UPDATE_BOOKINGS_SUCCESS,
                    payload: data
                });

                this._router.navigate(['/bookings/view', data.resnr]);
            }, (error: any) => {
                this._store.dispatch({
                    type: BookingTypes.UPDATE_BOOKINGS_FAILURE,
                    error: error.error
                });
            });
        }
    }

    /**
     * [filter description]
     * @method  filter
     * @author jackfiallos
     * @version [version]
     * @date    2017-11-09
     * @param   {any} name [description]
     * @return  {any[]} [description]
     */
    public filter(name: any): any[] {
        return this.guestOptions.filter((option) => {
            const selected = (name && typeof name === 'object') ? name.name : name;
            return option.name.toLowerCase().indexOf(selected.toLowerCase()) >= 0;
        });
    }

    /**
     * [displayFn description]
     * @method  displayFn
     * @author jackfiallos
     * @version [version]
     * @date    2017-11-09
     * @param   {any} option [description]
     * @return  {any} [description]
     */
    public displayFn(option: any): any {
        return option ? option.name : option;
    }
}
