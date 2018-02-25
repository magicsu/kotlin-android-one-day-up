package me.magicsu.one.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * {
"id": 2,
"channel": "Eyepetizer",
"title": "二十年里，你经历了什么？",
"description": "时光荏苒，来自荷兰独立电影导演的这部「Growth」在同一个屋子，使用一镜到底的方式记录了这个家庭二十年来的兴衰起落。你的二十年又有哪些变化呢？From Sil van der Woerd",
"playUrl": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=85936&editionType=default&source=aliyun",
"category": "剧情",
"authorAvatar": "http://img.kaiyanapp.com/8581b06aa17c7dbe8970e4c27bbdbd98.png?imageMogr2/quality/60/format/jpg",
"authorName": "开眼剧情精选",
"coverFeed": "http://img.kaiyanapp.com/c07d3e4cc261e4894061fabd008ff2d2.jpeg?imageMogr2/quality/60/format/jpg",
"coverBlurred": "",
"coverDetail": "",
"thumbPlayUrl": null,
"publishTime": "2018-02-24",
"collectDate": "2018-02-25",
"planPublishDate": "2018-02-25",
"size": null,
"duration": 562,
"slogan": null,
"score": 5336,
"createdAt": 1519534711000,
"createdBy": null,
"updatedAt": null,
"updatedBy": null
}
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class VideoResource(var id: Long,
                         var title: String,
                         var description: String,
                         var playUrl: String,
                         var category: String?,
                         var authorAvatar: String?,
                         var authorName: String?,
                         var coverFeed: String?,
                         var coverBlurred: String?,
                         var coverDetail: String?,
                         var thumbPlayUrl: String?,
                         var publishTime: String,
                         var collectDate: String,
                         var planPublishDate: String,
                         var size: Long,
                         var duration: Short,
                         var slogan: String?,
                         var score: Float) : Parcelable