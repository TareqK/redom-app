/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import {el,setChildren} from 'redom'
import {ShopOrder} from '../component/shop-order'
export class Shop {
    constructor(){
        this.el = el('div.shop')
    }
    
    update(params){
        if(params.length == 2){
            if(params[1] == "order"){
               setChildren(this.el,[new ShopOrder(params[0])])
            }
        }else if(params.length==1){
          
        }else{
            
        }
    }
}

