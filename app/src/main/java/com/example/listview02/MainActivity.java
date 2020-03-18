package com.example.listview02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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

        binding.roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("리스트뷰 아이템 클릭", String.format("%d번 줄 클릭",position));
//                클릭된 방으 ㅣ정보를 목록에서 빼옴.
                Room clickedRoom = roomDatas.get(position);

//                방 상세 화면으로 이동.
                Intent intent = new Intent(mContext, RoomDetailActivity.class);
                intent.putExtra("room", clickedRoom);
                startActivity(intent);
            }
        });

        binding.roomListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                해당 방의 설명을 Toast로 출력
                Room data = roomDatas.get(position);

                return true;
            }
        });

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
