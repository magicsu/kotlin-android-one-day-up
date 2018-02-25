package me.magicsu.one.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import me.magicsu.one.BaseFragment
import me.magicsu.one.R
import me.magicsu.one.model.VideoResource
import me.magicsu.one.util.VideoListener

/**
 * Created by sushun on 2018/2/22.
 */
class VideoDetailFragment : BaseFragment(), VideoDetailContract.View {

    override lateinit var presenter: VideoDetailContract.Presenter
    private lateinit var player: StandardGSYVideoPlayer
    private lateinit var videoResource: VideoResource
    private lateinit var orientationUtils: OrientationUtils

    private var isPlaying: Boolean = false
    private var isPaused: Boolean = false

    companion object {
        private const val TAG: String = "VideoDetailFragment"
        const val VIDEO_ITEM: String = "VIDEO_ITEM"

        fun newInstance(itemList: VideoResource) =
                VideoDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(VIDEO_ITEM, itemList)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_video_detail, container, false)
        with(root) {
            player = findViewById(R.id.player)
        }

        videoResource = arguments.getParcelable(VIDEO_ITEM)

        setupView()
        setupVideoPlayer()

        return root
    }

    override fun setupView() {
        videoResource?.let {

        }
    }

    override fun setupVideoPlayer() {
        videoResource?.let {
            player.setUp(videoResource?.playUrl, false, null, null)
        }

        player.titleTextView.visibility = View.GONE
        player.backButton.visibility = View.VISIBLE
        orientationUtils = OrientationUtils(activity, player)
        player.setIsTouchWiget(true)
        player.isRotateViewAuto = false
        player.isLockLand = false
        player.isShowFullAnimation = false
        player.isNeedLockFull = true
        player.fullscreenButton.setOnClickListener {
            orientationUtils.resolveByClick()
            player.startWindowFullscreen(activity, true, true)
        }

        player.setStandardVideoAllCallBack(object : VideoListener() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                orientationUtils.isEnable = true
                isPlaying = true
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                orientationUtils?.let { orientationUtils.backToProtVideo() }
            }
        })
        player.setLockClickListener { _, lock ->
            orientationUtils.isEnable = !lock
        }
        player.backButton.setOnClickListener({
            onBackPressed()
        })
    }

    fun onBackPressed() {
        orientationUtils?.let {
            orientationUtils.backToProtVideo()
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(activity)) {
            return
        }
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

}