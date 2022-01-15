package es.jrhtfld.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import es.jrhtfld.data.Profile

@Database(
    entities = [Profile::class],
    version = 1
)
abstract class ProfileDataBase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}