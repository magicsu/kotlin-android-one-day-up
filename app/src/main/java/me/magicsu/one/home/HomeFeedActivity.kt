package me.magicsu.one.home

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import me.magicsu.one.BaseActivity
import me.magicsu.one.R

class HomeFeedActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "OneDayUp"

        supportFragmentManager
                .findFragmentById(R.id.contentFrame) as HomeFeedFragment?
                ?: HomeFeedFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
    }
}
