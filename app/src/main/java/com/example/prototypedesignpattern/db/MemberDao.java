package com.example.prototypedesignpattern.db;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface MemberDao {
    @Insert
    public void insert(Member...items);
    @Update
    public void update(Member...items);
    @Delete
    public void delete(Member item);

    @Query("SELECT * FROM member")
    public List<Member> getAllMembers();

    @Query("SELECT * FROM member WHERE id = :id")
    public Member getItemById(Long id);
}
