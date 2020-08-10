import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {

  formModel: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  get formControls() { return this.formModel.controls; }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formModel = this.formBuilder.group({
      nome: ['', [Validators.required]],
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.formModel.invalid) {
      return;
    }

    this.usuarioService.createUsuario(this.formModel.value)
      .subscribe(data => {
        console.log(data);
        this.toastr.success('Usuário cadastrado com sucesso');
        this.router.navigate(['']);
      }, error => {
        console.log(error);
        error.error.errors.forEach(element => {
          this.toastr.error(element.message);
        });

      });

  }

}
