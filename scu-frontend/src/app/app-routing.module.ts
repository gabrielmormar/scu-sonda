import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormUsuarioComponent } from './form-usuario/form-usuario.component';
import { ListUsuarioComponent } from './list-usuario/list-usuario.component';


const routes: Routes = [
  { path: '', component: ListUsuarioComponent },
  { path: 'cadastro', component: FormUsuarioComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
