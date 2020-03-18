package com.example.listview02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listview02.R;
import com.example.listview02.datas.Room;

import java.util.List;

public class RoomAdapter extends ArrayAdapter<Room> {

    Context mContext;
    List<Room> mList;
    LayoutInflater inf;

    public RoomAdapter(@NonNull Context context, int resource, @NonNull List<Room> objects) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.room_list_item, null);
        }

        Room data = mList.get(position);

        TextView priceTxt = row.findViewById(R.id.priceTxt);
        TextView addressAndFloorTxt = row.findViewById(R.id.addressAndFloorTxt);
        TextView descTxt = row.findViewById(R.id.descTxt);

//        가격 설정 => setText에는 int값 넣지 말자.
//        1만 이상? 억 단위, 아니면? 숫자만 ,찍어서.
        if(data.getPrice() >= 10000) {
//            ?억 %,로 가공.
            int uk = data.getPrice()/10000;
            int thousand = data.getPrice()%10000;

            priceTxt.setText(String.format("%d억 %,d",uk,thousand));

        }
        else {
            priceTxt.setText(String.format("%,d", data.getPrice()));
        }

//        주소 / 층수 결합해서.

        String floorStr = "";
        if(data.getFloor() > 0 ) {
            floorStr = String.format("%d층", data.getFloor());
        }
        else if(data.getFloor() == 0) {
            floorStr = "반지하";
        }
        else {
            floorStr = String.format("지하 %d층", data.getFloor()*-1);
        }


        addressAndFloorTxt.setText(String.format("%s, %s", data.getAddress(), floorStr));

        return row;
    }

}
