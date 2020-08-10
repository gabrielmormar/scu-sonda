import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { Observable } from 'rxjs';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-usuario',
  templateUrl: './list-usuario.component.html',
  styleUrls: ['./list-usuario.component.css']
})
export class ListUsuarioComponent implements OnInit {
  usuarios: Observable<Usuario[]>;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.usuarios = this.usuarioService.getUsuarios();
    console.log(this.usuarios);
  }

}
