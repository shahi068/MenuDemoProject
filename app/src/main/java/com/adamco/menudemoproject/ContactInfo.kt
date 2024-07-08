package com.adamco.menudemoproject

import androidx.annotation.DrawableRes

data class ContactInfo(val name : String, val number : String, @DrawableRes val contactImg :Int)
