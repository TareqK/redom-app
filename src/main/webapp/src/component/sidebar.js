/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
export class Sidebar {
    constructor() {
        this.el = el('.col-md-1 .p-0 .m-0',
                [el('h1', 'sidebar')])
        this.disable()
    }

    toggle(){
        if(this.shown == false){
            this.show()
        }else{
            this.hide()
        }
    }

    disable() {
        this.el.classList.toggle('d-none', true)
        let event = new CustomEvent('sidebarDisabled')
        document.body.dispatchEvent(event)
        this.disabled = true
        
    }

    enable() {
        this.el.classList.toggle('d-none', false)
        let event = new CustomEvent('sidebarEnabled')
        document.body.dispatchEvent(event)
        this.disabled = false
        
    }

    update(nextView, params) {
        if (nextView == 'default' || nextView == 'login' || nextView == 'signup') {
            this.disable()
        } else {
            if(this.disabled){
                this.enable()
            }
        }
    }
}

