package saha.profile.subham.profile

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import saha.profile.subham.R
import saha.profile.subham.adapter.ProfileFragmentAdapter
import saha.profile.subham.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var profileBinding: ActivityProfileBinding
    private lateinit var profileFragmentAdapter: ProfileFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        val background: Drawable? = ContextCompat.getDrawable(this, R.drawable.background)
        window.setBackgroundDrawable(background)

        initialize()
    }

    private fun initialize() {
        profileFragmentAdapter = ProfileFragmentAdapter(supportFragmentManager, lifecycle)

        profileBinding.profileTab.addTab(profileBinding.profileTab.newTab().setText(resources.getString(R.string.application)))
        profileBinding.profileTab.addTab(profileBinding.profileTab.newTab().setText(resources.getString(R.string.settings)))

        profileBinding.viewPager2.adapter = profileFragmentAdapter

        profileBinding.profileTab.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    profileBinding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        profileBinding.viewPager2.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                profileBinding.profileTab.selectTab(profileBinding.profileTab.getTabAt(position))
            }
        })
    }
}