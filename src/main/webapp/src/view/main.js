/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el,text} from 'redom'

import * as L from 'leaflet'
import {ShopOverview} from '../component/shop-overview'
export class Main {
    constructor() {
        this.el = el('div#main')
        delete L.Icon.Default.prototype._getIconUrl;

        L.Icon.Default.mergeOptions({
            iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
            iconUrl: require('leaflet/dist/images/marker-icon.png'),
            shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
        });

    }
    update(params) {
        
        navigator.geolocation.getCurrentPosition((position) => {          
            this.startMap(position.coords.latitude, position.coords.longitude)
        }, (e) => {
            this.startMap(31.771959, 35.217018)
        }, {
            enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 0
        });

    }
    
    startMap(latitude,longitude){
         this.map = L.map('main').setView([latitude, longitude], 18);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map)
            this.getShops()
    }

    getShops() {
        fetch('/shop', {
            method: 'get',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
        }).then((response) => {
            if (response.status != 200) {
                throw new Error('Error Retreiving Shops')
            } else {
                return response.json()

            }
        }).then((res) => {
            if (res.length > 0) {
                res.map((shop) => {
                    if (shop.name && shop.takingOrders == true) {
                        L.marker([shop.latitude, shop.longitude]).addTo(this.map)
                                .bindPopup(new ShopOverview(shop).el)
                    }
                })
            }
        })
    }
}

