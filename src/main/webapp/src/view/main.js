/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'

import * as L from 'leaflet'
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
            this.map = L.map('main').setView([position.coords.latitude, position.coords.longitude], 13);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map)
            this.getShops()
        }, (e) => {
            this.map = L.map('main').setView([31.771959, 35.217018], 13);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map)
            this.getShops()
        }, {
            enableHighAccuracy: true,
            timeout: 5000,
            maximumAge: 0
        });

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
                                .bindPopup(shop.name)
                                .openPopup()
                    }
                })
            }
        })
    }
}

