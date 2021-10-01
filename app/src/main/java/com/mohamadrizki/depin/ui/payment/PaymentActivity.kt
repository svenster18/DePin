package com.mohamadrizki.depin.ui.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.databinding.ActivityPaymentBinding
import com.mohamadrizki.depin.ui.pemesanan.PemesananActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val price = intent.getIntExtra(PemesananActivity.EXTRA_PRICE, 0)
        val accountNumber = "2780 8544 3355 2121"

        binding.tvPaymentAmountContainer.text = price.toString()
        binding.tvAccountNumberContainer.text = accountNumber
    }
}