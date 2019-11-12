package com.example.prototypedesignpattern.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MemberDao {
    @Insert
    void insert(Member...items);
    @Update
    void update(Member...items);
    @Delete
    void delete(Member item);
    @Query("SELECT * FROM member")
    List<Member> getAllMembers();
    @Query("SELECT * FROM member WHERE id = :id")
    Member getItemById(Long id);
    @Query("DELETE FROM member WHERE id = :id")
    void delete(String id);
    @Query("DELETE FROM member")
    void deleteAll();
}
