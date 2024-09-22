package com.moataz.core.network.data.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moataz.core.network.data.local.model.BugEntityLocal

@Dao
interface BugDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBug(bug: BugEntityLocal)

    @Query("SELECT * FROM bugs")
    suspend fun getAllBugs(): List<BugEntityLocal>
}
