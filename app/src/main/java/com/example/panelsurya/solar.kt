package com.example.panelsurya

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.lang.Float
import java.util.*

data class solar(var ldrb:String?=null, var ldrd:String?=null, var nilairain:String?=null,
                var pos:String?=null, var time:String?=null, var vin:String?=null)