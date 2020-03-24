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
     * @param {type} middlewares a list of functions to execute in order before routing.
     *  If a middleware returns a name of a view differnt than the current target, then the following
     *  middlewares will not be called and a redirect to the returned view is done.
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
     * skipped. Otherwise, middlewares are executed.
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
                let result = this._execMiddleware(this.currentView, view, params);
                if (result && result) {
                     goto(result,params)
                }else{
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
     * Executes middlewares. if a middleware returns the name of the view other
     * than the current view, then the following middlewares will not be executed
     * and a redirect to the returned view is done
     * @param {type} currentView
     * @param {type} newView
     * @param {type} params
     * @returns {Boolean}
     */
    _execMiddleware(currentView, newView, params) {
        if (this.middlewareList.length > 0) {
            let i = 0;
            for (i in this.middlewareList) {
                let result = this.middlewareList[i].exec(currentView, newView, params);
                if (result && result != newView) {
                    return result
                }
               
            }
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



