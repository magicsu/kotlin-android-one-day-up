package me.magicsu.one.home

import me.magicsu.one.BasePresenter
import me.magicsu.one.BaseView
import me.magicsu.one.response.DailyVideoResponse

/**
 * Created by sushun on 2018/2/21.
 */
interface HomeFeedContract {

    interface View : BaseView<Presenter> {

        fun setupData(data: DailyVideoResponse)
    }

    interface Presenter : BasePresenter {

        fun loadData(pageNum: Int)
    }
}