import { router, mount, el, text} from 'redom';
import {App, goto} from 'redom-router';
class Home {
    constructor() {
        this.el = el("h1", "home");
    }

    update(data) {
        console.log(data);
    }
}

class Admin {
    constructor() {
        this.el = el("h1", "admin");
    }

    update(data) {
        console.log(data);
    }
}

class AdminMiddleware {
    constructor() {

    }

    exec(currentView, nextView, params) {
        if (nextView == 'admin') {
            return true;
        } else {
            return false;
        }
    }

}


const app = new App().routes({
    home: Home,
    default: Home,
    admin: Admin
}).middlewares([new AdminMiddleware()]).start()


