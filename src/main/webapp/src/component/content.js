/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
export class Content {
    constructor() {
        this.el = el('.col-md-12')
        document.body.addEventListener('sidebarDisabled', (e) => {
            this.fill();
        })

        document.body.addEventListener('sidebarEnabled', (e) => {
            this.normal();
        })
    }

    fill() {
         this.el.setAttribute('class' ,'col-md-12')
    }

    normal() {
         this.el.setAttribute('class' ,'col-md-11')
    }

    update(nextView, params) {

    }
}

