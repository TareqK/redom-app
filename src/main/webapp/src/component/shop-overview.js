/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
import {getBase64Url} from '../utils/utils'
import {goto} from 'redom-app'
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
                el('p', {}, [
                    el('p', this.data.address),
                    el('strong', 'telephone : ' + this.data.telephoneNumber)
                ])]),
            el('div.shop-footer', [

                this.orderButton = el('button.order-button .btn .btn-primary', 'Order Now', {
                    onclick: (e) => {
                        goto('shop',[this.data.id,'order'])
                    }
                })
            ])

        ])

    }
}

