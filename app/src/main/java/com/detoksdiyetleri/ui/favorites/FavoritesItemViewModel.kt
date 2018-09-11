package com.detoksdiyetleri.ui.favorites

import com.detoksdiyetleri.base.BaseRecyclerItemViewModel
import com.detoksdiyetleri.model.Diet

class FavoritesItemViewModel : BaseRecyclerItemViewModel<Diet>() {

    var title : String?=null

    var content : String?=null

    var imgUrl : String?=null

    override fun setValues() {
        title = model.title
        content = model.content
        imgUrl = model.imgUrl
    }

}