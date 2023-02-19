package com.yapp.buddycon.domain.model

data class CouponInputInfo(
    var id: Long = -1,
    var imageUrl: String = "",
    var barcode: String = "",
    var name: String = "",
    var expireDate: String = "",
    var storeName: String = "",
    var sentMemberName: String = "",
    var memo: String = "",
)
