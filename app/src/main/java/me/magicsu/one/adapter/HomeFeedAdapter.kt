package me.magicsu.one.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import me.magicsu.one.R
import me.magicsu.one.detail.VideoDetailActivity
import me.magicsu.one.detail.VideoDetailFragment
import me.magicsu.one.model.VideoResource

/**
 * Created by sushun on 2018/2/18.
 */
class HomeFeedAdapter(private var context: Context,
                      private var data: List<VideoResource>?
) : RecyclerView.Adapter<HomeFeedAdapter.PhotoFeedViewHolder>() {

    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoFeedViewHolder {
        return PhotoFeedViewHolder(mLayoutInflater?.inflate(R.layout.view_item_home_feed, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: PhotoFeedViewHolder, position: Int) {
        var entity = data?.get(position)

        entity?.let {
            Glide.with(context)
                    .load(entity?.coverFeed)
                    .centerCrop()
                    .crossFade()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.bannerIv)

            holder.titleTv.text = entity.title?: ""
            holder.descriptionTv.text = entity.description?: ""
            holder.categoryTv.text = entity.category?: ""

            holder.bannerIv.setOnClickListener {
                val intent = Intent(context, VideoDetailActivity::class.java)
                intent.putExtra(VideoDetailFragment.VIDEO_ITEM, data?.get(position))
                context.startActivity(intent)
            }
        }
    }

    fun setupData(data: List<VideoResource>?) {
        this.data = data
    }

    class PhotoFeedViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var bannerIv = itemView?.findViewById(R.id.banner) as ImageView
        var titleTv = itemView?.findViewById(R.id.title) as TextView
        var descriptionTv = itemView?.findViewById(R.id.description) as TextView
        var categoryTv = itemView?.findViewById(R.id.category) as TextView
        var playBtn = itemView?.findViewById(R.id.play) as ImageView
        var shareIv = itemView?.findViewById(R.id.share) as ImageView
        var favoriteIv = itemView?.findViewById(R.id.favorite) as ImageView
    }
}