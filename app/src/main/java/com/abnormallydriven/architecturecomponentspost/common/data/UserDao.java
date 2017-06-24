package com.abnormallydriven.architecturecomponentspost.common.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long inserUser(User user);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users")
    LiveData<User[]> getAllUsers();

    @Query("SELECT * from users where id = :userId LIMIT 1")
    LiveData<User> getUserById(long userId);

}
