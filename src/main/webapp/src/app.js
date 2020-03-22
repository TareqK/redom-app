import { router, mount, el, text} from 'redom';
import './styles/app.css';
import './styles/bootstrap.min.css';
import {App, goto} from 'redom-router';
class Home {
    constructor() {
        this.el = el('div', [
            el('h1','home'),
            el('button','auth me',{onclick:(e)=>{localStorage.setItem("auth",true);goto('admin')}}),
            el('button','dont auth me',{onclick:(e)=>{localStorage.removeItem("auth");goto('admin')}})
        ]);
    }

    update(data) {
    }
}

class Admin {
    constructor() {
        this.el = el('div',[
            el("h1", "admin"),
             el('button','Go Home',{onclick:(e)=>{localStorage.removeItem("auth");goto('home')}})
        ]);
    }

    update(data) {
    }
}

class AdminMiddleware {
    constructor() {

    }

    exec(currentView, nextView, params) {
        if (localStorage.getItem('auth') == undefined && nextView != 'home') {
             alert("You tried to redirect when you are not authenticated !")
             return 'home'
        }
        return nextView
       
    }

}


const app = new App().routes({
    home: Home,
    default: Home,
    admin: Admin
}).middlewares([new AdminMiddleware()]).start()

