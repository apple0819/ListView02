package com.example.listview02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.listview02.adapters.RoomAdapter;
import com.example.listview02.databinding.ActivityMainBinding;
import com.example.listview02.datas.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    List<Room> roomDatas = new ArrayList<>();
    RoomAdapter roomAdapter = null;
    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        roomAdapter = new RoomAdapter(mContext, R.layout.room_list_item, roomDatas);
        binding.roomListView.setAdapter(roomAdapter);

        addRooms();
    }

    private void addRooms() {
        roomDatas.add(new Room(8000, "서울시 은평구", 1, "살기 좋은 방입니다."));
        roomDatas.add(new Room(18000, "서울시 도봉구", 2, "살기 좋은 방입니다."));
        roomDatas.add(new Room(4000, "경기도 부천시", -1, "싸게나온 방입니다."));
        roomDatas.add(new Room(45000, "경기도 고양시", 0, "살기 좋은 방입니다."));
        roomDatas.add(new Room(800000, "서울시 강남구", 4, "비싼 방입니다."));

        roomAdapter.notifyDataSetChanged();

    }


}
