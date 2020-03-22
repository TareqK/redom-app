/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el} from 'redom'
export class Orders{
    constructor(){
        this.el = el('div.orders',
        [el('h1','orders')])
    }
    update(params){
        
    }
}