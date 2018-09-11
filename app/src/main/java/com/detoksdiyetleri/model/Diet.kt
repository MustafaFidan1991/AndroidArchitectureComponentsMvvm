package com.detoksdiyetleri.model

import com.detoksdiyetleri.base.BaseModel

class Diet(

) : BaseModel(){
    var id : String = ""
    var title : String = ""
    var content:String = ""
    var imgUrl:String = ""
    var materials:List<String> ?=null
    var createdDate : String = ""
    var favoriteCount : Int = 0
    var shareCount : Int = 0
}