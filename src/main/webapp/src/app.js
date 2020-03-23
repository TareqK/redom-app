import { router, mount, el, text} from 'redom'
import './style/app.css'
import './style/bootstrap.min.css'
import 'leaflet/dist/leaflet.css' 
import {App, goto} from 'redom-router'
import {Sidebar} from './component/sidebar'
import {Content} from './component/content'
import {Orders} from './view/orders'
import {MyShop} from './view/myshop'
import {Main} from './view/main'
import {Login} from './view/login'
import {SignUp} from './view/signup'
import * as L from 'leaflet' 

const header = el('nav.navbar')
const sidebar = new Sidebar()
const content = new Content();
localStorage.removeItem('role') 
localStorage.setItem('auth', 'false')
class SidebarMiddleware {
    constructor(sidebar, content) {
        this.sidebar = sidebar;
        this.content = content;
    }

    exec(currentView, nextView, params) {
        this.sidebar.update(nextView, params)
        this.content.update(nextView, params)
        return nextView
    }

}

class AuthMiddleware {
    constructor() {

    }

    exec(currentView, nextView, params) {
        if (nextView == 'default' || nextView == 'login' || nextView == 'signup') {
            return nextView
        } else if (localStorage.getItem('auth') == 'false') {
            return 'login'
        } else {
            return nextView
        }

    }
}
const page = el('.container-fluid', [
    header,
    el('.row', [sidebar, content])
])
mount(document.body, page)

const app = new App().routes({
    orders: Orders,
    myshop: MyShop,
    main: Main,
    default: Login,
    login: Login,
    signup: SignUp
}).middlewares([new AuthMiddleware(), new SidebarMiddleware(sidebar, content)]).start(content)



