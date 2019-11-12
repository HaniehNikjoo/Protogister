package com.example.prototypedesignpattern;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.prototypedesignpattern.db.AppDatabase;
import com.example.prototypedesignpattern.db.MemberDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    LinearLayoutManager linearlayout;
    ReportAdapter adapter;
    MemberDao itemDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        initList();
    }

    private void init(){
        ButterKnife.bind(ListActivity.this);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        itemDAO = database.getMemberDAO();
    }

    private void initList(){
        linearlayout = new LinearLayoutManager(App.context , LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearlayout);
        fillList();
    }

    private void fillList(){
        adapter = new ReportAdapter((i) -> itemDAO.delete(i),itemDAO.getAllMembers());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.activity_list_add_member)
    public void onClickAdd(){
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }
    @OnClick(R.id.activity_list_delete_all)
    public void onClickDeleteAll(){
        itemDAO.deleteAll();
        fillList();
    }
}
