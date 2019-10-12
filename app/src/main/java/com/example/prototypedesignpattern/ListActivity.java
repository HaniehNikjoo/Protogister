package com.example.prototypedesignpattern;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.prototypedesignpattern.db.AppDatabase;
import com.example.prototypedesignpattern.db.MemberDao;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager linearlayout;
    ReportAdapter adapter;
    MemberDao itemDAO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(ListActivity.this);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        itemDAO = database.getMemberDAO();

        linearlayout = new LinearLayoutManager(App.context , LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearlayout);
        adapter = new ReportAdapter(itemDAO.getAllMembers());
        recyclerView.setAdapter(adapter);
    }
}
