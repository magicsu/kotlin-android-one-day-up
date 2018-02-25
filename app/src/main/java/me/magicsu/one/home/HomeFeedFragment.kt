package me.magicsu.one.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ryan.rv_gallery.AnimManager
import com.ryan.rv_gallery.GalleryRecyclerView
import me.magicsu.one.BaseFragment
import me.magicsu.one.R
import me.magicsu.one.adapter.HomeFeedAdapter
import me.magicsu.one.response.DailyVideoResponse

/**
 * Created by sushun on 2018/2/21.
 */
class HomeFeedFragment : BaseFragment(), HomeFeedContract.View,
        GalleryRecyclerView.OnItemClickListener {

    private lateinit var galleryRecyclerView: GalleryRecyclerView
    private var homeFeedAdapter: HomeFeedAdapter? = null

    override lateinit var presenter: HomeFeedContract.Presenter

    companion object {
        const val TAG: String = "HomeFeedFragment"

        fun newInstance() = HomeFeedFragment()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home_feed, container, false)
        with(root) {
            galleryRecyclerView = findViewById(R.id.galleryRecyclerView)
        }

        galleryRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeFeedAdapter = HomeFeedAdapter(activity, null)
        galleryRecyclerView.adapter = homeFeedAdapter

        galleryRecyclerView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(20, 10)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.15f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setOnItemClickListener(this)                          // 设置点击事件

        presenter = HomeFeedPresenter(context, this)

        return root
    }

    override fun setupData(data: DailyVideoResponse) {
        homeFeedAdapter?.setupData(data?.data?.list)
        homeFeedAdapter?.notifyDataSetChanged()
    }

    override fun onItemClick(p0: View?, p1: Int) {
    }
}