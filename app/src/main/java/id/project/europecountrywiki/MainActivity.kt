package id.project.europecountrywiki

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.project.europecountrywiki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listEuropeCountry = ArrayList<EuropeCountry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.rvEuropeCountry.setHasFixedSize(true)
        listEuropeCountry.addAll(getEuropeCountryList())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvEuropeCountry.layoutManager = LinearLayoutManager(this)
        } else {
            binding.rvEuropeCountry.layoutManager = GridLayoutManager(this, 2)
        }
        val listEuropeCountryAdapter = ListEuropeCountryAdapter(listEuropeCountry)
        binding.rvEuropeCountry.adapter = listEuropeCountryAdapter

        listEuropeCountryAdapter.setOnItemClickCallback(object :
            ListEuropeCountryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: EuropeCountry) {
                val intentToDetailActivity = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetailActivity.putExtra(DetailActivity.EUROPE_COUNTRY, data)
                startActivity(intentToDetailActivity)
            }
        })
    }

    private fun getEuropeCountryList(): ArrayList<EuropeCountry> {
        val dataEuropeCountryName = resources.getStringArray(R.array.europe_country_name)
        val dataEuropeCountryDescription =
            resources.getStringArray(R.array.europe_country_description)
        val dataEuropeCountryFlag = resources.getStringArray(R.array.europe_country_flag)
        val dataEuropeCountryCapital = resources.getStringArray(R.array.europe_country_capital)
        val dataEuropeCountryImage = resources.getStringArray(R.array.europe_country_image)
        val dataEuropeCountryPopulation =
            resources.getStringArray(R.array.europe_country_population)
        val dataEuropeCountryLink = resources.getStringArray(R.array.europe_country_link)
        val list = ArrayList<EuropeCountry>()

        for (i in dataEuropeCountryName.indices) {
            val europeCountry = EuropeCountry(
                dataEuropeCountryName[i],
                dataEuropeCountryDescription[i],
                dataEuropeCountryFlag[i],
                dataEuropeCountryCapital[i],
                dataEuropeCountryImage[i],
                dataEuropeCountryPopulation[i].toDouble(),
                dataEuropeCountryLink[i]
            )
            list.add(europeCountry)
        }
        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intentAboutMe = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(intentAboutMe)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}