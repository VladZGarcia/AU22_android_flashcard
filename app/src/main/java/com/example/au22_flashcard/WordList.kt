package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WordList: AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    private lateinit var db : AppDatabase
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val wordList = mutableListOf<Word>()
    private val usedWords = mutableListOf<Word>()
  //  val defWordlist = loadAllWords()
  //  launch {
  //      val list = defWordlist.await()
  //      for(word in list){
  //          wordList.add(word)
  //      }
  //      showNewWord()
//
  //  }
   // job = Job()
//
   // db = Room.databaseBuilder(applicationContext,
   // AppDatabase::class.java,
   // "word_items")
   // .fallbackToDestructiveMigration()
   // .build()


    //alternativ 1
    fun getNewWord() : Word {
        if (wordList.size == usedWords.size) {
            usedWords.clear()
        }

        var word : Word? = null

        do {
            val rnd = (0 until wordList.size).random()
            word = wordList[rnd]
        } while(usedWords.contains(word))

        usedWords.add(word!!)

        return word
    }
    fun loadAllWords() : Deferred<List<Word>> =
        async(Dispatchers.IO) {
            db.wordDao().getAll()
        }



}

//  init {
//      initializeWords()
//
//  }


 //  fun initializeWords() {
 //      val word = Word(0,"Hello", "Hej")
 //      (word)
 //      wordList.add(Word(0,"Good bye", "Hej då"))
 //      wordList.add(Word(0,"Thank you", "Tack"))
 //      wordList.add(Word(0,"Welcome", "Välkommen"))
 //      wordList.add(Word(0,"Computer", "Dator"))
//
 //  }

//    fun getNewWord() : Word {
//        val rnd = (0 until wordList.size).random()
//        return wordList[rnd]
//    }


// alternativ 3
//    fun getNewWord() : Word {
//        if(wordList.isEmpty() ) {
//            initializeWords()
//        }
//
//        val rnd = (0 until wordList.size).random()
//        val word = wordList.removeAt(rnd)
//
//        return word
//    }
// 1. en till lista med redan använda ord
// 2. lista med index på använda ord
// 3. använt ord tas bort från listan
// 4. ordet håller reda på om det redan är använt








