package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewWordActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    private lateinit var db : AppDatabase
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var sweWordView: EditText
    lateinit var engWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        job = Job()
        db = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "word_items")
            .fallbackToDestructiveMigration()
            .build()

        sweWordView = findViewById(R.id.swedishWord)
        engWordView = findViewById(R.id.englishWord)

        val button = findViewById<Button>(R.id.addButton)

        button.setOnClickListener {

            val sweText = sweWordView.text.toString()
            val engText = engWordView.text.toString()
            val word = Word(0,"$engText","$sweText")
           saveWord(word)
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }



    }
    fun saveWord(word: Word) {

        launch(Dispatchers.IO) {
            db.wordDao().insert(word)
        }
    }
}