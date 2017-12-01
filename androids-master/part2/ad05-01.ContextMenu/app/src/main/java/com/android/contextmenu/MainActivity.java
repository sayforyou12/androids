package com.android.contextmenu;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);

        // 위젯과 contextMenu 연결
        registerForContextMenu(btn1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.button){
            inflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);
        switch (item.getItemId()) {

            case R.id.itempRed:
                layout.setBackgroundColor(Color.RED);
                break;
            case R.id.itempBlue:
                layout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.itempGreen:
                layout.setBackgroundColor(Color.GREEN);
                break;



        }
        return true;
    }
}
