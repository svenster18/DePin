package com.mohamadrizki.depin.ui.pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.databinding.ActivityPemesananBinding
import com.mohamadrizki.depin.ui.home.HomeFragment
import com.mohamadrizki.depin.ui.payment.PaymentActivity

class PemesananActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shipClass = intent.getStringExtra(HomeFragment.EXTRA_SHIP_CLASS)
        val shipPrice = price(shipClass)

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
            binding.spTo.adapter = adapter
        }

        binding.btnBook.setOnClickListener {
            val intent = Intent(this@PemesananActivity, PaymentActivity::class.java)
            intent.putExtra(EXTRA_PRICE, shipPrice)
            startActivity(intent)
        }
    }

    fun price(shipClass: String?): Int {
        when (shipClass) {
            HomeFragment.ECONOMY -> return 250000
            HomeFragment.BUSINESS -> return 500000
            HomeFragment.EXECUTIVE -> return 1000000
            else -> return 0
        }
    }

    companion object {
        const val EXTRA_PRICE = "extra_price"
    }
}