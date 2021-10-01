package com.mohamadrizki.depin.data.model

import java.sql.Date
import java.sql.Time

data class Pemesanan(
    val noTransaksi : String,
    val userId: String? = null,
    val from: String,
    val to: String,
    val dayDate: Date,
    val departure: Time,
    val passengers: Int,
    val shipClass: String
)
