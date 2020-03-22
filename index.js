import { router, mount} from 'redom'

        /**
         * A Simple App with a simple router
         * @type type
         */
export class App {
    constructor() {
        this.middlewareList = []
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
        this.currentView = this.defaultRoute
        return this
    }

    /**
     * 
     * @param {type} middlewares a list of functions to execute in order before routing. If a function returns false, then the routing will not continue and the view will not change,
     * and all other middlewares after will not be called
     * @returns {App}
     */
    middlewares(middlewares) {
        if (middlewares) {
            this.middlewareList = middlewares
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
        window.onhashchange = (e) => {
            this._doRoute()
        }
        window.onload = (e) => {
            this._doRoute()
        }
        return this
    }
    /**
     * Routes to the new view. If it is the same as the current view, middleware execution is 
     * skipped. Otherwise, middlewares are executed. If the middleware fails, then the current 
     * view is refreshed
     * @returns {undefined}
     */
    _doRoute() {
        let routeUrl = window.location.hash.substr(1).split('/')
        let view = routeUrl[0]
        let paramString = window.location.hash.substr(1 + view.length)
        let params = routeUrl.splice(1, routeUrl.length).filter((param) => {
            return param.length > 0;
        })
        if (this.app.Views[view]) {
            if (view !== this.currentView) {
                if (this._execMiddleware(this.currentView, view, params) == true) {
                    this.currentView = view
                    this.app.update(view, params)
                }
            } else {
                this.currentView = view
                this.app.update(view, params)
            }
        } else {
            window.location.hash = this.defaultRoute + paramString
        }
    }

    /**
     * Executes middlewares. if a middleware returns false, then the current 
     * view is refreshed
     * @param {type} currentView
     * @param {type} newView
     * @param {type} params
     * @returns {Boolean}
     */
    _execMiddleware(currentView, newView, params) {
        if (this.middlewareList.length > 0) {
            let i = 0;
            for (i in this.middlewareList) {
                if (this.middlewareList[i].exec(currentView, newView, params) == false) {
                    goto(currentView, params);
                    return false
                }
            }
        }
        return true;
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



