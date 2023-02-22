package com.yapp.buddycon.presentation.ui.giftcon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.buddycon.domain.model.GiftConDetail
import com.yapp.buddycon.domain.usecase.delete.DeleteCouponUseCase
import com.yapp.buddycon.domain.usecase.giftcon.GetCouponDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GiftConDetailViewModel @Inject constructor(
    private val getCouponDetailUseCase: GetCouponDetailUseCase,
    private val deleteCouponUseCase: DeleteCouponUseCase
) : ViewModel() {

    private val _couponDetailState = MutableSharedFlow<GiftConDetail>(replay = 1)
    val couponDetailState = _couponDetailState.asSharedFlow()

    private val _giftconUserEvent = MutableSharedFlow<GiftConUserEvent>()
    val giftConUserEvent = _giftconUserEvent.asSharedFlow()

    private val _couponExpireDateState = MutableStateFlow("")
    val couponExpireDateState = _couponExpireDateState.asStateFlow()
    val couponTitleState =  MutableStateFlow("")
    val usePlaceState = MutableStateFlow("")
    val couponMemoState = MutableStateFlow("")
    val checkPriceCouponState = MutableStateFlow(false)
    val leftMoneyCouponState = MutableStateFlow("")

    fun getGiftconDetailInfo(giftId: Int) {
        getCouponDetailUseCase(giftId)
            .catch { e -> Timber.e("getGiftconDetailInfo error ${e.localizedMessage}") }
            .onEach { _couponDetailState.emit(it) }
            .launchIn(viewModelScope)
    }

    fun deleteCoupon(giftId: Int) {
        deleteCouponUseCase(giftId)
            .catch { e -> Timber.e("deleteCoupon error ${e.localizedMessage}") }
            .onEach { _giftconUserEvent.emit(GiftConUserEvent.Delete) }
            .launchIn(viewModelScope)
    }

    fun changeExpireDate(date: String){
        _couponExpireDateState.value = date
    }

    fun setPricesCoupon(isMoneyCoupon: Boolean){
        checkPriceCouponState.value = isMoneyCoupon
    }

    fun changePricesCoupon(){
        checkPriceCouponState.value = checkPriceCouponState.value.not()
    }

    fun setLeftMonyCoupon(leftMoney: Int){
        leftMoneyCouponState.value = leftMoney.toString()
    }
}