package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Swipe delete
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // ✅ Semua link dijamin bisa tampil di browser (direct image URLs)
        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Luna", "Loves moonlight and quiet naps.", "https://cdn2.thecatapi.com/images/b1r.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Oscar", "Always sleepy but very photogenic.", "https://cdn2.thecatapi.com/images/bpc.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Shadow", "Sneaky, fast, and loves climbing curtains.", "https://cdn2.thecatapi.com/images/bpc.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Cleo", "Has attitude, and she knows it.", "https://cdn2.thecatapi.com/images/2oo.gif"),
                CatModel(Gender.Unknown, CatBreed.ExoticShorthair, "Whiskers", "Purring machine 24/7.", "https://cdn2.thecatapi.com/images/a9b.jpg"),
                CatModel(Gender.Female, CatBreed.BalineseJavanese, "Misty", "Always hides under the couch but super cute.", "https://cdn2.thecatapi.com/images/MTc4NDM3Mw.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Leo", "King of the house with royal demands.", "https://cdn2.thecatapi.com/images/1no.jpg")
            )
        )
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
