package com.detoksdiyetleri.base

import com.google.gson.Gson
import java.io.Serializable

open  class BaseModel() : Serializable{




    fun toJsonString() : String{
        return Gson().toJson(this)
    }
}