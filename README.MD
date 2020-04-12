[![npm version](https://badge.fury.io/js/redom-app.svg)](https://badge.fury.io/js/redom-app)
# Redom App
A simple Single Page Application Framework Built on the [RE:DOM](https://github.com/redom/redom)
library.

## Installation

```bash
 npm install redom-app
```
## Getting Started

In your application entry point, add 
```javascript
import {App} from 'redom-app'

/*some code*/
const app = new App().routes({
    home: Home,
    default: Home,
}).start()
```

where ```default``` is the default view if no other views are specified and  each
key:value pair is the name of the route and the View class.

Each view looks like this
```javascript
class Home {
    constructor() {
        this.el = el('div', [//needs to be the property this.el
            el('h1','home'),
            // Rest of your elements, just like in RE:DOM
        ]);
    }

    update(params) {
        //Do whatever you want with the parameters
    }
}
```

## Redirection


```javascript
import {goto} from 'redom-app'
//some code here

goto('view-name',['param1','param2'])
```


## Middleware

```javascript

class SomeMiddleware {
    constructor() {

    }
    //this is what the middleware method looks like
    exec(currentView, nextView, params) {
        if(//some condition here){
            return 'other-view'// the view to redirect to. Stops execution of other middlewares
        }else{
            return nextView
        }
       
    }

}
/*in your entry point*/
const app = new App().routes({
    home: Home,
    default: Home,
}).middlewares([new SomeMiddleware()]).start()
```

## Example URLs
* ```http://mysite.com/#default ```
* ```http://mysite.com/#orders/1243-234-134-1234/delivery ```
* ```http://mysite.com/#my-profile/edit ```


## More Examples
Examples can be found in [here](https://github.com/TareqK/redom-app/blob/master/example/app.js)

## TypeScript
aodjfkoajdfpoasdf aiosdfj aoisdjf oaisjdfo iajsdfoi jasdif
```javascript
loader : [
 'typescipt':{'param1':stuff}
]
```
