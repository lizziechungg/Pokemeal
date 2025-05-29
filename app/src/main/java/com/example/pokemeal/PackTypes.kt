package com.example.pokemeal

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class PackTypes(
    var types: List<PackType>
): Parcelable

@Parcelize
data class PackType(
    var name: String,
    var resourceId: String
): Parcelable

