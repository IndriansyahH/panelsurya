package com.example.panelsurya

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.panelsurya.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mDatabaseRef : DatabaseReference? = null
    private var mFirebaseInstanse : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val fragment = HomeFragment.newInstance()
        addFragment(fragment)

        setUpTabBar()

        mFirebaseInstanse = FirebaseDatabase.getInstance()
        mDatabaseRef = mFirebaseInstanse!!.getReference("Juara").child("realtime")

        mDatabaseRef!!.child("nilairain").addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val hujan: Int = value.toInt()
                    if (hujan <= 500){
                        val notificationHelper = NotificationHelper(this@MainActivity)
                        notificationHelper.sendHighPriorityNotification("Kondisi Hujan Mohon Hemat Penggunaan Battery",
                            "", MainActivity::class.java)
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("vin").addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val vin: Int = value.toInt()
                    if (vin <= 10){
                        val notificationHelper = NotificationHelper(this@MainActivity)
                        notificationHelper.sendHighPriorityNotification("Battery <= 10v Mohon Hemat Penggunaan Battery",
                            "", MainActivity::class.java)
                    }
                    if (vin <= 8){
                        val notificationHelper = NotificationHelper(this@MainActivity)
                        notificationHelper.sendHighPriorityNotification("Battery Habis!",
                            "", MainActivity::class.java)
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )

//        val notificationHelper = NotificationHelper(this@MainActivity)
//                    notificationHelper.sendHighPriorityNotification("Battery Habis!",
//                        "", HomeFragment::class.java)
    }

    private fun setUpTabBar() {

        binding.bottomNavBar.setOnItemSelectedListener {
            when (it) {
                R.id.home ->{
                    val fragment = HomeFragment.newInstance()
                    addFragment(fragment)
//                    return@OnNavigationItemSelectedListener true
                }
                R.id.history ->{
                    val fragment = HistoryFragment.newInstanse()
                    addFragment(fragment)
//                    return@OnNavigationItemSelectedListener true
                }
                R.id.setting ->{
                    val fragment = SettingFragment.newInstanse()
                    addFragment(fragment)
//                    return@OnNavigationItemSelectedListener true
                }
//                R.id.home -> binding.textMain.text = "Near"
////                R.id.history -> binding.textMain.text = "Chat"
//                R.id.history -> {
//                    binding.textMain.text = "Profile"
////                    binding.bottomNavBar.showBadge(R.id.history)
//                }
//                R.id.setting -> {
//                    binding.textMain.text = "Settings"
////                    binding.bottomNavBar.dismissBadge(R.id.setting)
//                }


            }
        }

    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(com.google.android.material.R.anim.design_bottom_sheet_slide_in, com.google.android.material.R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}

//class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener,
//    HistoryFragment.OnFragmentInteractionListener {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val tabLayout = findViewById(R.id.tablayout) as TabLayout
//        tabLayout.addTab(tabLayout.newTab().setText("Home"))
//        tabLayout.addTab(tabLayout.newTab().setText("History Data"))
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//        val viewPager = findViewById(R.id.pager) as ViewPager
//        val adapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
//        viewPager.adapter = adapter
//        viewPager.setOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager.currentItem = tab.position
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
//    }
//
//    override fun onFragmentInteraction(uri: Uri?) {}
//}