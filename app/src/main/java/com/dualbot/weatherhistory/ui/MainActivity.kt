package com.dualbot.weatherhistory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.dualbot.weatherhistory.R
import com.dualbot.weatherhistory.ui.mainFragment.MainFragment
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import com.dualbot.weatherhistory.ui.storageFragment.StorageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var toolbarAnimations: ToolbarAnimations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbarAnimations = ToolbarAnimations(this)
        if (savedInstanceState == null)
            attachFragment(MainFragment(toolbarAnimations))
        println("Activity onCreate")
        setupViewModel()
        setupObserver()
    }

    override fun onStart() {
        println("Activity onStart")
        super.onStart()
    }

    override fun onResume() {
        println("Activity onResume")
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> viewModel.actionRefreshPressed(getCurrentFragment())
            R.id.action_storage -> viewModel.actionStoragePressed(getCurrentFragment())
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        viewModel.rotateActionRefresh()
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        viewModel.backPressed(getCurrentFragment())
        super.onBackPressed()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.MainActivityModelFactory(this, toolbarAnimations))[MainActivityViewModel::class.java]
    }

    private fun setupObserver() {
        viewModel.refreshIcon.observe(this) {
            findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_refresh).icon = it
        }
        viewModel.fragment.observe(this) {
            attachFragment(it)
        }
    }

    private fun attachFragment(fragment: Fragment) {
//        if (fragment is StorageFragment) {
//            supportFragmentManager
//                .beginTransaction()
//                .setCustomAnimations(
//                    R.anim.slide_in_animation,
//                    R.anim.slide_out_animation,
//                    R.anim.fade_in_animation,
//                    R.anim.fade_out_animation
//                )
//                .replace(R.id.fragment_container, fragment)
//                .commitNow()
        supportFragmentManager.commit {
            when (fragment) {
                is MainFragment -> {
                    setCustomAnimations(
                        R.anim.main_fragment_slide_in_animation,
                        R.anim.fade_out_animation,
                        R.anim.fade_in_animation,
                        R.anim.main_fragment_slide_out_animation
                    )
                }
                is StorageFragment -> {
                    setCustomAnimations(
                        R.anim.storage_fragment_slide_in_animation,
                        R.anim.fade_out_animation,
                        R.anim.fade_in_animation,
                        R.anim.storage_fragment_slide_out_animation
                    )
                }
            }
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun getCurrentFragment() =
        supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private fun setActionRefreshIconRefresh() {
        println("StorageFragment setActionRefreshIconRefresh")
        val refreshItem =
            findViewById<Toolbar>(R.id.toolbar)
                ?.menu
                ?.findItem(R.id.action_refresh)
        val icon = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_baseline_brightness_7_24,
            theme)
        refreshItem?.icon = icon
    }
}