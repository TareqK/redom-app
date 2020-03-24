/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
import {goto} from 'redom-app'
export class SignUp {
    constructor() {
        this.el = el('div.login .row',
                [el('.col-4 .offset-4 .login-form', [
                        el('.row', [
                            el('h1.text-center', 'Welcome'),
                            el('hr'),
                        ]),

                        el('form', [
                            el('.form-row', [
                                el('div.form-group .col-6', [
                                    el('label', 'Phone Number', {for : 'phone'}),
                                    el('input.form-control', {required: true, id: 'phone', type: 'text', onchange: (e) => {
                                            this.phoneNumber = e.target.value
                                        }})
                                ]),
                                el('div.form-group .col-6', [
                                    el('label', 'username', {for : 'username'}),
                                    el('input.form-control', {required: true, id: 'username', type: 'text', onchange: (e) => {
                                            this.username = e.target.value
                                        }})
                                ])
                            ]),
                            el('.form-row', [
                                el('div.form-group .col-6', [
                                    el('label', 'I Am a...', {for : 'role'}),
                                    el('select.form-control', [
                                        el('option', 'Please Select ....', {disabled: true, selected: true, value: ''}),
                                        el('option', 'Customer', {value: 'ROLE_CUSTOMER'}),
                                        el('option', 'Barista', {value: 'ROLE_BARISTA'})
                                    ], {required: true, id: 'role', onchange: (e) => {
                                            this.userRole = e.target.value
                                        }})
                                ]),
                                el('div.form-group .col-6', [
                                    el('label', 'password', {for : 'password'}),
                                    el('input.form-control', {required: true, id: 'password', type: 'password', onchange: (e) => {
                                            this.password = e.target.value
                                        }})
                                ])
                            ]),

                            el('div.form-group .row', [
                                el('.col-12 .d-flex .justify-content-end', [
                                    el('button.btn .btn-primary', 'Sign Up', {type: 'submit'})
                                ])
                            ])
                        ], {onsubmit: (e) => {
                                this.signup()
                            }})
                    ])
                ])
    }

    signup() {
        let data = {
            'username': this.username,
            'password': this.password,
            'userRole': this.userRole,
            'telephoneNumber': this.phoneNumber,
        }
        fetch('/user/signup', {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then((response) => {
            if (response.status != 200) {
                alert("Error Signing Up")
            } else {
                goto('login')
            }
        })
    }

    update(params) {

    }
}

