import { GuestsListComponent } from './list/guests.list.component';
import { GuestsFormComponent } from './form/guests.form.component';
import { GuestsDetailComponent } from './detail/guests.detail.component';

export const routes = [{
    path: '',
    children: [{
        path: '',
        component: GuestsListComponent
    }, {
        path: 'create',
        component: GuestsFormComponent
    }, {
        path: 'edit/:id',
        component: GuestsFormComponent,
        data:{kind:'new'}
    },
    {
        path: 'edit/exist/:name',
        component: GuestsFormComponent,
        data:{kind:'exist'}
    }, {
        path: 'view/:id',
        component: GuestsDetailComponent,
        data: { kind: 'byid' }
    }, {
        path: 'view/byname/:name',
        component: GuestsDetailComponent,
        data: { kind: 'byname' }
    }
    ]
}];
