package com.mohamadrizki.depin.ui.pemesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.databinding.ActivityPemesananBinding
import com.mohamadrizki.depin.ui.home.HomeFragment

class PemesananActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shipClass = intent.getStringExtra(HomeFragment.EXTRA_SHIP_CLASS)

        binding.tvShipClassContainer.text = shipClass

        ArrayAdapter.createFromResource(
            this,
            R.array.city,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spFrom.adapter = adapter
        }
    }
}