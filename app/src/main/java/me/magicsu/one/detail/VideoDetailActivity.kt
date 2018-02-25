package me.magicsu.one.detail

import android.os.Bundle
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import me.magicsu.one.BaseActivity
import me.magicsu.one.R
import me.magicsu.one.detail.VideoDetailFragment.Companion.VIDEO_ITEM
import me.magicsu.one.model.VideoResource

/**
 * Created by sushun on 2018/2/22.
 */
class VideoDetailActivity: BaseActivity() {

    private lateinit var detailFragment: VideoDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)

        // Get video list item resource.
        var item: VideoResource = intent.getParcelableExtra(VIDEO_ITEM)

        detailFragment = supportFragmentManager
                .findFragmentById(R.id.contentFrame) as VideoDetailFragment?
                ?: VideoDetailFragment.newInstance(item).also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
    }

    override fun onBackPressed() {
        detailFragment.onBackPressed()
        super.onBackPressed()
    }
}