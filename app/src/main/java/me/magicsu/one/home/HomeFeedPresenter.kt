package me.magicsu.one.home

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.magicsu.one.net.RequestManager

/**
 * Created by sushun on 2018/2/21.
 */
class HomeFeedPresenter(
        private var context: Context,
        private var view: HomeFeedContract.View
) : HomeFeedContract.Presenter {

    companion object {
        private const val TAG: String = "HomeFeedPresenter"
        private const val PAGE_SIZE = 10
    }

    override fun start() {
        loadData(1)
    }

    override fun loadData(pageNum: Int) {
        RequestManager.getInstance().getApiService().dailyVideos(1, pageNum, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setupData(it)
                }, {
                    Log.e(TAG, it.message)
                })
    }
}