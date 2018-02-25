package me.magicsu.one.detail

import me.magicsu.one.BasePresenter
import me.magicsu.one.BaseView
import me.magicsu.one.model.VideoResource

/**
 * Created by sushun on 2018/2/21.
 */
interface VideoDetailContract {

    interface View : BaseView<Presenter> {

        fun setupView()

        fun setupVideoPlayer()
    }

    interface Presenter : BasePresenter {

    }
}