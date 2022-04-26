package com.example.bytechallengue

import com.example.bytechallengue.api.MyDataItem
}

class OnClickListener (val clickListener: (myDataItem: MyDataItem) -> Unit){
    fun onClick(myDataItem: MyDataItem) = clickListener(myDataItem)
}