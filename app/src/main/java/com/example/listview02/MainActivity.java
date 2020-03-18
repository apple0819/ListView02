package com.example.listview02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                해당 방의 설명을 Toast로 출력
//                Room data = roomDatas.get(position);
//                Toast.makeText(mContxt, data.getDescription(), Toast. ).show();

//                지우기 전에, 정말 지울건지? 확인받자.
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("방 삭제 확인");
                alert.setMessage("정말 이 방을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        얼럿에서도 확인을 누른 경우.
                        //                꾹 누르면, 해당 아이템을 목록에서 삭제.
                        roomDatas.remove(position);
//                어댖터에게 새로고침 시킴.
                        roomAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("취소", null);
                alert.show();


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
