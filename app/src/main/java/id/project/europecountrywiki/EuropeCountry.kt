package id.project.europecountrywiki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EuropeCountry(
    val name: String,
    val description: String,
    val flag: String,
    val capitalCity: String,
    val image: String,
    val population: Double,
    val linkWiki: String
) : Parcelable
