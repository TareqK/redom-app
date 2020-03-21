import { router, mount} from 'redom'

/**
 * A Simple App with a simple router
 * @type type
 */
export class App {
    constructor() {

    }
    /**
     * 
     * @param {type} routes the dictionary of views and names to redirect by
     * @param {type} defaultRoute the default route, if found. otherwise, the route 'default' is used
     * @returns {App}
     */
    routes(routes, defaultRoute) {
        this.app = router('div.app', routes)
        if (defaultRoute) {
            this.defaultRoute = defaultRoute
        } else {
            this.defaultRoute = 'default'
        }
        return this
    }

    /**
     * Starts the Application at this base element on the dom. If no base element is provided, then the 
     * <body> is the base element
     * @param {type} appBase the base element to use 
     * @returns {App}
     */
    start(appBase) {
        if (appBase) {
            mount(appBase, this.app)
        } else {
            mount(document.body, this.app);
        }
        window.onhashchange = this._doRoute()
        window.onload = this._doRoute()
        return this
    }

    _doRoute() {
        let routeUrl = window.location.hash.substr(1).split('/')
        let view = routeUrl[0]
        let paramString = window.location.hash.substr(1 + view.length)
        let params = routeUrl.splice(1, routeUrl.length)
        if (this.app.Views[view]) {
            this.app.update(view, params)
        } else {
            window.location.hash = this.defaultRoute + paramString
        }
    }

}

export const goto = (view, params) => {
    let paramString = ""
    if (params && params.length > 0) {
        params.map((param) => {
            paramString = paramString.concat('/').concat(param)
        })
    }
    window.location.hash = view + paramString
}



