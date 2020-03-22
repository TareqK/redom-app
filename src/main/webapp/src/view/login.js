/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import {el} from 'redom'
import {goto} from 'redom-router'
export class Login {
    constructor() {
        this.el = el('div.login .row',
                [el('.col-4 .offset-4 .login-form', [
                        el('.row', [
                            el('h1.text-center', 'Welcome'),
                            el('hr'),
                        ]),
                        el('form', [
                            el('div.form-group', [
                                el('label', 'username', {for : 'username'}),
                                el('input.form-control', {required: true, id: 'username', type: 'text', onchange: (e) => {
                                        this.username = e.target.value
                                    }})
                            ]),
                            el('div.form-group', [
                                el('label', 'password', {for : 'password'}),
                                el('input.form-control', {required: true, id: 'password', type: 'password', onchange: (e) => {
                                        this.password = e.target.value
                                    }})
                            ]),
                            el('div.form-group .row', [
                                el('.col-6 .px-0 .mx-0 .d-flex .justify-content-start', [
                                    el('a', 'Sign Up', {href: "/#signup"})
                                ]),
                                el('.col-6 .px-0 .mx-0 .d-flex .justify-content-end', [
                                    el('button.btn .btn-primary', 'Sign In', {type: 'submit'})
                                ])
                            ])
                        ], {onsubmit: (e) => {
                                this.login()
                            }})
                    ])
                ])
    }

    login() {
        let data = {
            'username': this.username,
            'password': this.password
        }
        fetch('/user/signin', {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then((response) => {
            if (response.status != 200) {
                alert("Username Or Password Wrong")
                throw new Error('Login Failure')
            } else {
                return response.json()

            }
        }).then((res) => {
             localStorage.setItem('role',res.userRole)
             localStorage.setItem('auth',true)
            if (res.userRole == "ROLE_BARISTA") {
                goto('myshop')
            } else {
                goto('main')
            }
        })
    }
    update(params) {

    }
}