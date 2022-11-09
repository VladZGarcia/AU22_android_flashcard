package com.example.au22_flashcard

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    // delete



    // getAllwords





}