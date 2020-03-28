/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
import {getBase64Url} from '../utils/utils'
export class ShopOverview {
    constructor(shopData) {
        this.data = shopData
        this.el = el('div.shop-details', {}, [
            el('div.shop-details-header', [
                el('h3', this.data.name),
                el('div')
            ]),
            el('div.shop-content', {}, [
                el('img.shop-image', {src: this.data.image}),
                el('div', {}, [
                    el('h5', this.data.telephoneNumber)
                ])]),
            el('div.shop-footer', [
                this.button = el('button.order-button', 'Order Now', {
                    onclick: (e) => {
                        console.log(e);
                    }
                })
            ])

        ])
        if (this.data.takingOrders === false) {
            this.el.classList.add('inactive')
            this.button.setAttribute('disabled', true)
        } else {
            this.el.classList.remove('inactive')
            this.button.removeAttribute('disabled')
        }

    }
}

