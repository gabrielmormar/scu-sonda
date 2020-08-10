import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './models/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  baseUrl: string = 'http://localhost:8080/usuarios/';

  constructor(private http: HttpClient) { }
  
  getUsuarios() : Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.baseUrl);
  }

  createUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.baseUrl, usuario);
  }
}
