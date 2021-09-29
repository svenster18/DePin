package com.mohamadrizki.depin.ui.pemesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohamadrizki.depin.databinding.ActivityPemesananBinding
import com.mohamadrizki.depin.ui.home.HomeFragment

class PemesananActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shipClass = intent.getStringExtra(HomeFragment.EXTRA_SHIP_CLASS)

        binding.tvShipClassContainer.text = shipClass
    }
}