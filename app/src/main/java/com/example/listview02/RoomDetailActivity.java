package com.example.listview02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.listview02.databinding.ActivityRoomDetailBinding;
import com.example.listview02.datas.Room;

public class RoomDetailActivity extends BaseActivity {

    ActivityRoomDetailBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_detail);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//        첨부된 방 데이터를 받아서 화면에 출력.
        Room room = (Room) getIntent().getSerializableExtra("room");

        binding.priceTxt.setText(room.getFormattedPrice());
        binding.priceTxt.setText(room.getFloorToString());
        binding.addressTxt.setText(room.getAddress());
        binding.descTxt.setText(room.getDescription());


    }
}
