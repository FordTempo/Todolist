package sg.edu.rp.c346.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   Spinner spn;

    EditText etList;

    Button btnAdd;

    Button btnDelete;
    Button btnClear;
    ListView tvList;

    ArrayList<String> alList;
    ArrayAdapter<String> aaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etList = findViewById(R.id.editList);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        btnClear = findViewById(R.id.buttonClearItem);
        tvList = findViewById(R.id.listViewList);
        spn = findViewById(R.id.spinner);
        btnDelete.setEnabled(false);

        ArrayList<String> alList;

        alList = new ArrayList<String>();

        aaList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alList);

        tvList.setAdapter(aaList);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etList.setHint("Type in a new task here");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String text = etList.getText().toString();
                                alList.add(text);
                                btnDelete.setEnabled(false);
                                aaList.notifyDataSetChanged();
                                etList.setText(null);
                            }
                        });


                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alList.removeAll(alList);
                                aaList.notifyDataSetChanged();
                                etList.setText(null);
                            }

                        });
                        break;
                    case 1:
                        etList.setHint("Type in the index of task to be removed");
                        btnDelete.setEnabled(true);
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alList.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                                }else if(etList.getText()!=alList){
                                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                                } else if (alList.isEmpty()==false){
                                    int num = Integer.parseInt(etList.getText().toString());
                                    alList.remove(num);
                                    aaList.notifyDataSetChanged();
                                    etList.setText(null);
                                } else{

                                }
                            }
                        });
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

    }
}