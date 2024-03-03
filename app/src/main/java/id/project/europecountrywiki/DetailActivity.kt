package id.project.europecountrywiki

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.project.europecountrywiki.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var bindingDetail : ActivityDetailBinding
    private lateinit var linkItem: String

    companion object {
        const val EUROPE_COUNTRY = "europe_country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail"

        // Getting Europe Country data :
        val europeCountry = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<EuropeCountry>(EUROPE_COUNTRY, EuropeCountry::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<EuropeCountry>(EUROPE_COUNTRY)
        }

        if (europeCountry != null) {
            bindingDetail.countryName.text = europeCountry.name
            bindingDetail.countryAdditionalDetail.text = "Capital City - ${europeCountry.capitalCity}\n\nPopulation - ${europeCountry.population} million people"
            bindingDetail.countryDescription.text = europeCountry.description
            Glide.with(this).load(europeCountry.image).into(bindingDetail.placePhoto)
            Glide.with(this).load(europeCountry.flag).into(bindingDetail.flagPhoto)
            linkItem = europeCountry.linkWiki
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.setType("text/plain")
                intentShare.putExtra(Intent.EXTRA_TEXT, linkItem)
                startActivity(Intent.createChooser(intentShare, "Share Link"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}