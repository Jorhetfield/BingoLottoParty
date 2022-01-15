package es.jrhtfld.domain.room

import androidx.room.*
import es.jrhtfld.data.Profile

@Dao
interface ProfileDao {

    @Query("SELECT * FROM Profile")
    suspend fun getProfile(): Profile

    @Query("SELECT * FROM Profile WHERE id = :id")
    suspend fun getProfileById(id: Int): Profile

    @Update
    suspend fun updateProfile(profile: Profile)

    @Insert
    suspend fun insertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)
}