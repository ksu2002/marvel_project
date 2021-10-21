package com.example.marvelproject


import android.graphics.Color.red
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.marvelproject.data.database.AppDatabase
import com.example.marvelproject.data.ui.main.CharacterFragment
import com.example.marvelproject.data.ui.main.CharactersListFragment
import com.example.marvelproject.data.ui.main.FavoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

var active = Fragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppDatabase.invoke(applicationContext)
        val fragmentCharactersList = CharactersListFragment()
        val fragmentCharacter = CharacterFragment()
        val fragmentFavorite = FavoriteFragment()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(applicationContext, R.color.red))
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, fragmentFavorite, "fragmentFavorite")
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, fragmentCharacter, "fragmentCharacter")
            .hide(fragmentCharacter).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, fragmentCharactersList, "fragmentCharactersList")
            .hide(fragmentFavorite).commit()
        active = fragmentCharactersList
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.item_1 -> {
                    supportFragmentManager.beginTransaction().hide(active)
                        .show(fragmentCharactersList).commit();
                    active = fragmentCharactersList;
                    true
                }
                R.id.item_2 -> {
                    supportFragmentManager.beginTransaction().hide(active)
                        .show(fragmentFavorite).commit();
                    active = fragmentFavorite
                    true
                }
                R.id.item_3 -> {
                    System.exit(0);
                    true
                }

                else -> false
            }
        }
    }
}